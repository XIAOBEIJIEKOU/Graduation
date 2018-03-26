package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.StateEnums;

/**
 *	��װִ����ɱ��������� 
 */
public class SeckillExecution {
	//��ɱ��
	private long seckillId;
	//��ɱ��״̬
	private int state;
	//��ɱ״̬˵��
	private String stateInfo;
	//��ɱ��ϸ
	private SuccessKilled successKilled;
	
	/**
	 * 	����ɱ�ɹ�����������
	 * @param seckillId
	 * @param successKilled
	 */
	public SeckillExecution(long seckillId, StateEnums stateEnums, SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnums.getState();
		this.stateInfo = stateEnums.getStateInfo();
		this.successKilled = successKilled;
	}
	
	/**
	 * 	����ɱʧ�ܣ�����ʧ�ܵ�״̬��ԭ��
	 * @param seckillId
	 */
	public SeckillExecution(long seckillId, StateEnums stateEnums) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnums.getState();
		this.stateInfo = stateEnums.getStateInfo();
	}

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
	 * @return state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state Ҫ���õ� state
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return stateInfo
	 */
	public String getStateInfo() {
		return stateInfo;
	}

	/**
	 * @param stateInfo Ҫ���õ� stateInfo
	 */
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	/**
	 * @return successKilled
	 */
	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	/**
	 * @param successKilled Ҫ���õ� successKilled
	 */
	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}

	/* ���� Javadoc��
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state + ", stateInfo=" + stateInfo
				+ ", successKilled=" + successKilled + "]";
	}
	
	
	
}
