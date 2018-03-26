package org.seckill.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Administrator
 * 
 */
//����Junit����ʱ����springIOC(pom.xml:spring-test��Junit)
@RunWith(SpringJUnit4ClassRunner.class)
//����spring�������ļ�
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

	//ע��Daoʵ����
	@Resource
	private SeckillDao seckillDao;
	
	@Test
	public void testQueryById() {
		long id=2;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill);//todo δע�뵽entity��
	}
	
	@Test
	public void testQueryAll() {
		List<Seckill> list = seckillDao.queryAll(0, 10);
		for(Seckill seckill:list){
			System.out.println(seckill);
		}
		/**
		 * Caused by: org.apache.ibatis.binding.BindingException:
		 * Parameter 'offset' not found. Available parameters are [0, 1, param1, param2]
		 */
	}
	
	@Test
	public void testReduceNumber() {
		Date killTime = new Date();
		int linenum = seckillDao.reduceNumber(1, killTime);
		System.out.println(linenum);
	}


	

}
