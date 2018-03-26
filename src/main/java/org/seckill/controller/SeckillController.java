package org.seckill.controller;

import java.util.Date;
import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.StateEnums;
import org.seckill.exception.RepeatException;
import org.seckill.exception.SeckillClose;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * url:/ģ��/��Դ/{id}/ϸ�ֹ���
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
	
	@Autowired
	private SeckillService seckillService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "list";
	}

	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
		if (seckillId == null) {
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.getSeckill(seckillId);
		if (seckill == null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}

	/*
	 * ��Ӧǰ̨jQuery����Ajax����Json
	 */
	@RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable Long seckillId) {
		// ����һ�����صķ�װ���ݽ��
		SeckillResult<Exposer> result;
		try {
			//��¶��ɱ�ӿ�
			Exposer exposer = seckillService.exportSeckillUrlNoRedis(seckillId);
//			Exposer exposer = seckillService.exportSeckillUrlWithRedis(seckillId);
			result = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			result = new SeckillResult<Exposer>(false, e.getMessage());
		}
		return result;
	}

    /**
     * Jmeter����url��ȡ����md5�ļ��飬ȡ���˴�@Cookie�л�ȡ����userPhone��ȡ����seckill/{}/{}/execuition ��Restful·�ɣ�����ʹ��Get����
     * @param seckillId
     * @param userPhone
     * @return
     */
	@RequestMapping(value = "/execution", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResult<SeckillExecution> execute (@RequestParam("seckillId") Long seckillId, @RequestParam("userPhone") Long userPhone) {

//	@RequestMapping(value = "/{seckillId}/{userPhone}/execution", method = RequestMethod.POST, produces = {
//			"application/json;charset=UTF-8" })
//	@ResponseBody
//	public SeckillResult<SeckillExecution> execute (@PathVariable("seckillId") Long seckillId, @PathVariable("userPhone") Long userPhone) {
		//�ڲ��߼�����userphone�����򱨴�
		if (userPhone == null) {
			return new SeckillResult<SeckillExecution>(false,"δע��");
		}
		SeckillResult<SeckillExecution> result;
		//try-catch�����еĴ������������ɷ��ؽ��������
		try {
			//��ɱ˳������
//			SeckillExecution execution = seckillService.executeSeckillWithRedis(seckillId, userPhone);
//			SeckillExecution execution = seckillService.executeSeckillNoRedis(seckillId,userPhone);
			SeckillExecution execution = seckillService.executeSeckillAllRedis(seckillId,userPhone);
			return new SeckillResult<SeckillExecution>(true,execution);
		} catch (RepeatException e) {
			//�ظ���ɱ
			SeckillExecution execution = new SeckillExecution(seckillId, StateEnums.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(true,execution);
		} catch (SeckillClose e) {
			//��ɱ����
			SeckillExecution execution = new SeckillExecution(seckillId, StateEnums.END);
			return new SeckillResult<SeckillExecution>(true,execution);
		} catch (SeckillException e) {
			//δ֪��ϵͳ����
			SeckillExecution execution = new SeckillExecution(seckillId, StateEnums.SYSTEM_ERROR);
			return new SeckillResult<SeckillExecution>(true,execution);
		}
	}
	
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> time() {
		Date now = new Date();
		return new SeckillResult<Long>(true, now.getTime());
	}
}
