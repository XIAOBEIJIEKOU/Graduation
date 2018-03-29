/*������ɱ��Ʒ�б���*/
CREATE TABLE seckill(
seckillId bigint NOT NULL auto_increment ,
name varchar(100) NOT NULL ,
number int NOT NULL COMMENT '�������',
startTime timestamp NOT NULL COMMENT '��ɱ��ʼʱ��',
endTime timestamp NOT NULL COMMENT '��ɱ����ʱ��',
createTime timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '��¼����ʱ��',
PRIMARY KEY(seckillId),
key idx_start_time(startTime),
key idx_end_time(endTime),
key idx_create_time(createTime))

/*��������*/
insert into
	seckill(name,number,startTime,endTime)
values
	('1000Ԫ��ɱiPhone7','100','2017-5-2 00:00:00','2017-5-3 00:00:00');

/*������ʲô�ɹ������*/
CREATE TABLE success_killed(
seckillId bigint NOT NULL COMMENT '��ɱ��ƷID',
userPhone bigint NOT NULL COMMENT '�û��ֻ���',
state tinyint NOT NULL DEFAULT 0 COMMENT '��ǰ״̬��0��ʾnothing��1��ʾ�ѱ���ɱ',
createTime timestamp NOT NULL COMMENT '��ǰ��¼������ʱ��',
PRIMARY KEY(seckillId,userPhone),/*����������id���û��ֻ�����Ψһ��ʾ*/
key idx_create_time(createTime)
)