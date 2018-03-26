//package org.seckill.service.impl;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.seckill.dto.Exposer;
//import org.seckill.dto.SeckillExecution;
//import org.seckill.entity.Seckill;
//import org.seckill.exception.SeckillException;
//import org.seckill.service.SeckillService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
//public class SeckillServiceImplTest {
//
//	/**
//	 * ʹ�ýӿڣ�����ʹ��ʵ���ࣿʹ��ʵ�����Ǵ�ģ���ʾû�з���ע���bean impl������service���µ�
//	 */
//	@Autowired
//	private SeckillService seckillService;
//
//	@Test
//	public void testGetSeckillList() {
//		/**
//		 * Closing non transactional SqlSession
//		 */
//		List<Seckill> list = seckillService.getSeckillList();
//		for (Seckill seckill : list) {
//			System.out.println(seckill);
//		}
//	}
//
//	@Test
//	public void testGetSeckill() {
//		/**
//		 * Closing non transactional SqlSession
//		 */
//		long seckillId = 2;
//		Seckill seckill = seckillService.getSeckill(seckillId);
//		System.out.println(seckill);
//	}
//
//	@Test
//	public void testExportSeckillUrl() {
//		/**
//		 * Exposer [exposed=false, md5=null, seckillId=2, now=1493915770831,
//		 * start=1493654400000, end=1493740800000] Exposer [exposed=true,
//		 * md5=26c66c8766c1c0a92e993979ca89ab9d, seckillId=2, now=0, start=0,
//		 * end=0]
//		 */
//		long seckillId = 2;
//		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
//		System.out.println(exposer);
//	}
//
//	@Test
//	public void testExecuteSeckill() {
//		/**
//		 * org.seckill.exception.SeckillException: md5 ��Ϣ����д������Ȼ��ת if (md5 ==
//		 * null || !md5.equals(getMd5(seckillId))) { throw new SeckillException(
//		 * "md5 ��Ϣ����д������Ȼ��ת"); }
//		 */
//
//		/**
//		 * ����spring���� JDBC Connection
//		 * [com.mchange.v2.c3p0.impl.NewProxyConnection@26adfd2d] will be
//		 * managed by Spring
//		 */
//		long seckillId = 2;
//		long userPhone = 15150681502L;
//		String md5 = "26c66c8766c1c0a92e993979ca89ab9d";
//		/**
//		 * ��������try{}catch{} �����ظ���ɱ���Junit���Բ�ͨ��
//		 *
//		 */
//		try {
//			SeckillExecution seckillExecution = seckillService.executeSeckillNoRedis(seckillId, userPhone);
//			System.err.println(seckillExecution);
//		} catch (SeckillException e) {
//			// TODO: handle exception
//		}
//	}
//
//	@Test
//	/**
//	 * һ����Ԫ���������ɱ�߼��Ĳ��ԣ�md5��ֵ��exposer������
//	 */
//	public void testSeckill() {
//		long seckill_id = 2;
//		Exposer exposer = seckillService.exportSeckillUrl(seckill_id);
//		if (exposer.isExposed()) {
//			System.out.println(exposer);
//			long userPhone = 15150681502L;
//			String md5 = exposer.getMd5();
////			try {
//				System.out.println("---------------------------------��ɱ����start-----------------------------------");
//				SeckillExecution seckillExecution = seckillService.executeSeckillAllRedis(seckill_id, userPhone);
//				System.out.println("---------------------------------��ɱ����end-----------------------------------");
//				System.err.println(seckillExecution);
//			} catch (SeckillException e) {
//				// TODO: handle exception
//			}
//		} else {
//			System.out.println(exposer);
//		}
//	}
//
//	@Test
//	public void executeSeckillWithRedis() {
//	}
//
//	@Test
//	public void executeSeckillAllRedis() {
//	}
//}
