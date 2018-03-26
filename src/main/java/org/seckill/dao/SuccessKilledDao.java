package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {

	/**
	 * ���빺����ϸ��¼��ͨ��ID��phoneΨһ��ʾ�����ظ�
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

	/**
	 * ��ѯ������ϸ�����Ҵ��ع������Ʒ����Ϣ
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId ,@Param("userPhone") long userPhone);
}
