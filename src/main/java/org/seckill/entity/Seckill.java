package org.seckill.entity;

import java.util.Date;

/**
 * ���ݿ��е�seckill��
 * @author ZhuangYefan
 *
 */
public class Seckill {
	
	private  Long seckillId;	// ��ɱ��ƷId

	private String name;	// ��ɱ��Ʒ��
	
	private int number;		//	��ɱ��Ʒ����
	
	private Date startTime;	//��ɱ����ʱ��
	
	private Date endTime;	//��ɱ����ʱ��
	
	private Date createTime;

	/**
	 * @return seckillId
	 */
	public Long getSeckillId() {
		return seckillId;
	}

	/**
	 * @param seckillId Ҫ���õ� seckillId
	 */
	public void setSeckillId(Long seckillId) {
		this.seckillId = seckillId;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Ҫ���õ� name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number Ҫ���õ� number
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime Ҫ���õ� startTime
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime Ҫ���õ� endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
		return "Seckill [seckillId=" + seckillId + ", name=" + name + ", number=" + number + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", createTime=" + createTime + "]";
	}
	
	
}
