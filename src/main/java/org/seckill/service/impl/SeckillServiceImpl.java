package org.seckill.service.impl;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.StateEnums;
import org.seckill.exception.RepeatException;
import org.seckill.exception.SeckillClose;
import org.seckill.exception.SeckillException;
import org.seckill.jms.ProducerService;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class SeckillServiceImpl implements SeckillService {
	// ��־
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * ��spring�����в����Ѿ����ڵ�SeckillDao��SuccessKilledDao
	 * �ҵ�֮����Զ�ע�뵽service��ʹ��
	 * ����Ҫ�Լ� new һ���������
	 */
	@Autowired
	private SeckillDao seckillDao;
	@Autowired
	private SuccessKilledDao successKilledDao;
	@Autowired
	private RedisDao redisDao;
	@Autowired
	private ProducerService producerService;

	// ��ֵ
	private final String salt = "fmjsaoij544654";

	// ֻ���ڲ�ʹ�ò��뱩¶ ����private ������md5ֵ
	private String getMd5(long seckillId) {
		String base = seckillId + "/" + salt;
		return DigestUtils.md5DigestAsHex(base.getBytes());
	}

	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 10);
	}

	public Seckill getSeckill(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	/**
	 * ʹ��redis ��¶��ɱ�ӿ�
	 * @param seckillId
	 * @return
	 */
	public Exposer exportSeckillUrlWithRedis(long seckillId) {
		//	ÿһ��������ɱ���ᾭ�������¶�Ľӿڣ�������Ż�
		Seckill seckill = redisDao.getSeckill(seckillId);
		if (seckill == null) {
			seckill = seckillDao.queryById(seckillId);
			if (seckill == null) {
				return new Exposer(false, seckillId);
			}
			redisDao.putSeckill(seckill);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		//������ɱʱ�����
		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		String md5 = getMd5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	/**
	 * ��ʹ��redis ���汩¶��ɱ�ӿ�
	 * @param seckillId
	 * @return
	 */
	public Exposer exportSeckillUrlNoRedis(long seckillId) {
		//	ÿһ��������ɱ���ᾭ�������¶�Ľӿڣ�������Ż�
		Seckill seckill = seckillDao.queryById(seckillId);
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		//������ɱʱ�����
		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		String md5 = getMd5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	/**
	 * ����ע�����������ŵ㣺
	 * 1.�����ŶӴ��һֱԼ�����γɹ淶��������һ������������Ч��@Autowires�����е����ݿ����ȫ����Ϊ����
	 * 2.��֤�����ִ��ʱ�価���̣ܶ���Ҫ���������������HTTP�Ȼ��߰��뵽�����ⲿ������ getMd5()
	 * 3.ֻ��һ����������ֻ�в�ѯ��ʱ����Ҫ����������
	 */
	/**
	 * ����redis ����ֱ����ɱ
	 * @param seckillId
	 * @param userPhone
	 * @return
	 * @throws SeckillException
	 * @throws RepeatException
	 * @throws SeckillClose
	 */
	@Transactional
	public SeckillExecution executeSeckillNoRedis(long seckillId, long userPhone) throws SeckillException, RepeatException, SeckillClose {
	    Date now = new Date();
		try {
			//  ��¼������ϸ
			int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
			if (insertCount == 0) {
				throw new RepeatException("�ظ���ɱ");
			}
			//  �����
			int updateCount = seckillDao.reduceNumber(seckillId, now);
			if (updateCount == 0) {
				throw new SeckillClose("��ɱ����");
			}
			SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
			return new SeckillExecution(seckillId, StateEnums.SUCCESS, successKilled);
		} catch (SeckillClose seckillClose) {
			throw seckillClose;
		} catch (RepeatException repeatException) {
			throw repeatException;
		} catch (Exception e) {
			//  ���ݿ����Ӷ��ˣ����������ˡ�����
			throw new SeckillException("ϵͳ����");
		}
	}

	/**
	 * ����ֱ�Ӳ������ݿ⣬ֻ��������һ�����ݿ⼴����һ��redis��������ɱ����ǰ���жϿ��
	 * @param seckillId
	 * @param userPhone
	 * @return
	 * @throws SeckillException
	 * @throws RepeatException
	 * @throws SeckillClose
	 */
	@Transactional
	public SeckillExecution executeSeckillWithRedis(long seckillId, long userPhone) throws SeckillException, RepeatException, SeckillClose {
		Date killTime = new Date();
		Seckill seckill = null;
		//	�����Ǵ�exposer�ӿ�ת����ߵģ��Ǳ�Ȼredis�ж�Ӧ��Ʒ�Ļ��棬Redis�жϿ��
		if (redisDao.getSeckill(seckillId) != null) {
			seckill = redisDao.getSeckill(seckillId);
		} else {
			seckill = seckillDao.queryById(seckillId);
			redisDao.putSeckill(seckill);
		}
		if (seckill.getNumber() != 0) {
			//	��¼������ϸ
			int insertNum = successKilledDao.insertSuccessKilled(seckillId, userPhone);
			if (insertNum <= 0) {
				throw new RepeatException("�ظ���ɱ");
			} else {
				//	�����
				int updateNum = seckillDao.reduceNumber(seckillId, killTime);
				if (updateNum <= 0) {
					throw new SeckillException("ϵͳ����");
				}
				//	ÿ����һ�����ݿ⼴����һ��redis����
				redisDao.putSeckill(seckillDao.queryById(seckillId));
				SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
				//���ظ� controller ��dto
				return new SeckillExecution(seckillId, StateEnums.SUCCESS, successKilled);
			}
		} else {
			redisDao.deleteSeckill(seckill);
			throw new SeckillClose("��ɱ����");
		}
	}

	/**
	 * ��ɱ����ʱֻ����redis���ݿ⣬Ȼ��ͨ��ActiveMQ �첽����¼ˢ�� Mysql
	 * @param seckillId
	 * @param userPhone
	 * @return
	 * @throws SeckillException
	 * @throws RepeatException
	 * @throws SeckillClose
	 */
	public SeckillExecution executeSeckillAllRedis(long seckillId, long userPhone) throws SeckillException, RepeatException, SeckillClose {
		Seckill seckill = null;
		if (redisDao.getSeckill(seckillId) != null) {
			seckill = redisDao.getSeckill(seckillId);
		}else {
			seckill = seckillDao.queryById(seckillId);
			redisDao.putSeckill(seckill);
		}
		if (seckill.getNumber() != 0) {
			if (redisDao.isRepeatSeckill(seckillId , userPhone)){
				throw new RepeatException("�ظ���ɱ");
			}else{
				//  ����ִ����ɱ����
				if (redisDao.executeSeckill(seckill ,userPhone)){
                    //  ����ɱ��¼������Ϣ��ActiveMQ
                    ObjectMapper mapper = new ObjectMapper();
                    SuccessKilled successKilled = new SuccessKilled();
                    try {
                        successKilled.setSeckillId(seckillId);
                        successKilled.setUserPhone(userPhone);
                        String jsonMsg = mapper.writeValueAsString(successKilled);
                        //	����json��Ϣ
						producerService.sendQueueMessage(jsonMsg);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
					//  ���ظ� controller ��dto
					return new SeckillExecution(seckillId, StateEnums.SUCCESS);
				}else{
					throw new SeckillException("ϵͳ����");
				}
			}
		} else {
			redisDao.deleteSeckill(seckill);
			throw new SeckillClose("��ɱ����");
		}
	}
}
