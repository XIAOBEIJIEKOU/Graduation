package org.seckill.entity;

import java.util.Date;

public class SuccessKilled {

	private long seckillId;	// ��ɱ�ɹ���ƷId

	private long userPhone;	// ��ɱ�ɹ��û��ֻ���
	
	private short state;
	
	private Date createTime;
	
	//����������ϸ��Ӧͬһ����Ʒ���ڶ෽����¼��ʾ����
	private Seckill seckill;

	/**
	 * @return seckillId
	 */
	public long getSeckillId() {
		return seckillId;
	}

	/**
	 * @param seckillId Ҫ���õ� seckillId
	 */
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	/**
	 * @return userPhone
	 */
	public long getUserPhone() {
		return userPhone;
	}

	/**
	 * @param userPhone Ҫ���õ� userPhone
	 */
	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	/**
	 * @return state
	 */
	public short getState() {
		return state;
	}

	/**
	 * @param state Ҫ���õ� state
	 */
	public void setState(short state) {
		this.state = state;
	}

	/**
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime Ҫ���õ� createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/* ���� Javadoc��
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SuccessKilled [seckillId=" + seckillId + ", userPhone=" + userPhone + ", state=" + state
				+ ", createTime=" + createTime + "]";
	}

	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}
}
