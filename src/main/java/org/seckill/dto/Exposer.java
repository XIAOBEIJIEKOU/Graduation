package org.seckill.dto;

/**
 * ��װ������ɱ����ҳ������
 * ��¶��ɱ�ӿ�
 * ���ж�����캯��Ӧ�Բ�ͬ��ҵ���߼�
 */
public class Exposer {
	//�Ƿ�����ɱ
	private boolean exposed;
	//����ֵ
	private String md5;
	//��ɱ����ƷID
	private long seckillId;
	//ϵͳ��ǰʱ��
	private long now;
	//��ɱ����ʱ��
	private long startTime;
	//��ɱ����ʱ��
	private long endTime;
	/**
	 *  ����ɱ��������ҪID�ͼ�����Ϣ
	 * @param exposed
	 * @param md5
	 * @param seckillId
	 */
	public Exposer(boolean exposed, String md5, long seckillId) {
		super();
		this.exposed = exposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}
	/**
	 * 	����ɱû�п���������ʾ����ʱ��
	 * @param exposed
	 * @param now
	 * @param start
	 * @param end
	 */
	public Exposer(boolean exposed, long seckillId, long now, long start, long end) {
		super();
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.now = now;
		this.startTime = start;
		this.endTime = end;
	}
	/**
	 * 	
	 * @param exposed
	 * @param seckillId
	 */
	public Exposer(boolean exposed, long seckillId) {
		super();
		this.exposed = exposed;
		this.seckillId = seckillId;
	}

	/**
	 * @return exposed
	 */
	public boolean isExposed() {
		return exposed;
	}

	/**
	 * @param exposed Ҫ���õ� exposed
	 */
	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	/**
	 * @return md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * @param md5 Ҫ���õ� md5
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
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
	 * @return now
	 */
	public long getNow() {
		return now;
	}

	/**
	 * @param now Ҫ���õ� now
	 */
	public void setNow(long now) {
		this.now = now;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Exposer{" +
				"exposed=" + exposed +
				", md5='" + md5 + '\'' +
				", seckillId=" + seckillId +
				", now=" + now +
				", startTime=" + startTime +
				", endTime=" + endTime +
				'}';
	}
}
