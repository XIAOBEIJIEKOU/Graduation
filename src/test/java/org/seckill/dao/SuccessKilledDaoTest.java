package org.seckill.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

	@Resource
	private SuccessKilledDao successKilledDao;
	
	@Test
	public void testInsertSuccessKilled() {
		/**
		 * �ظ�ִ�е�ʱ��update = 0
		 * ��ʾ�������ظ����룬�����ᱨ������������ignore
		 */
		long seckillId = 2;
		long userPhone = 15150681501L;//���� int ������ 15150681501 �����˷�Χ
		int linenum = successKilledDao.insertSuccessKilled(seckillId, userPhone);
		System.out.println(linenum);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		long seckillId = 1;
		long userPhone = 15150681501L;
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
		System.out.println(successKilled);
		System.out.println(successKilled.getSeckill());
	}

}
