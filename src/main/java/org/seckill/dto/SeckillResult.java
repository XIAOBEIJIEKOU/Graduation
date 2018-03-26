package org.seckill.dto;

/**
 * ��װǰ̨�����Json��ʾ���
 * ����ajax���ص�json
 * 
 */
public class SeckillResult<T> {
	
	private boolean success;
	
	private T data;
	
	private String error;
	
	/*
	 * �ɹ�
	 */
	public SeckillResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}
	
	/*
	 * ʧ��
	 */
	public SeckillResult(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}

	/**
	 * @return success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success Ҫ���õ� success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data Ҫ���õ� data
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error Ҫ���õ� error
	 */
	public void setError(String error) {
		this.error = error;
	}
	
	
}
