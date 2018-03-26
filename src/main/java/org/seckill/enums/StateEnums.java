package org.seckill.enums;

/**
 * 	�����ֵ�
 * 	ö�٣���¼web��service��Ҫʹ�õĲ���
 * @author Administrator
 *
 */
public enum StateEnums {

	SUCCESS(1, "��ɱ�ɹ�"), 
	END(0, "��ɱ����"), 
	REPEAT_KILL(-1, "�ظ���ɱ"),
	DATA_MD5(-2, "���ݴ۸�"), 
	SYSTEM_ERROR(-3, "ϵͳ�쳣");

	private int state;

	private String stateInfo;

	private StateEnums(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	/**
	 * @return state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @return stateInfo
	 */
	public String getStateInfo() {
		return stateInfo;
	}

	public static StateEnums stateOf(int index) {
		for (StateEnums state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
