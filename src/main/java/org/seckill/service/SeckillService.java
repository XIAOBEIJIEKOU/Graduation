package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatException;
import org.seckill.exception.SeckillClose;
import org.seckill.exception.SeckillException;

/**
 * ҵ��ӿڣ� 1.������������ 2.���� 3.�������ͣ�return��ʵ�塢dto����exception��
 * 
 * @author Administrator
 *
 */
public interface SeckillService {

	/**
	 * 
	 */
	List<Seckill> getSeckillList();

	/**
	 * 
	 */
	Seckill getSeckill(long seckillId);

	/**
	 * ��¶��ɱ�ӿڵ�ַ ���������ɱ��ת����һ��url������ɱ ���û�п�������ʾʱ�����ɱ����ʱ
	 */
	Exposer exportSeckillUrlWithRedis(long seckillId);

	Exposer exportSeckillUrlNoRedis(long seckillId);

	/*
	 * ��Ҫ��֤md5��ֵ��ID��userphone
	 */
	SeckillExecution executeSeckillNoRedis(long seckillId, long userPhone)
			throws SeckillException, RepeatException, SeckillClose;

	SeckillExecution executeSeckillWithRedis(long seckillId, long userPhone)
			throws SeckillException, RepeatException, SeckillClose;

	SeckillExecution executeSeckillAllRedis(long seckillId, long userPhone)
			throws SeckillException, RepeatException, SeckillClose;
}
