//package org.seckill.dao.jms;
//
//import org.apache.activemq.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.jms.*;
//
///**
// * @Author Zhuang YeFan
// * @Date
// * @Description
//// **/
//public class ProducerDao2 {
//    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerDao2.class);
//    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
//    private static final String SUBJECT = "test-activemq-queue";
//
//    public void sendMsg() throws JMSException{
//        //��ʼ�����ӹ���
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
//        //�������
//        Connection conn = connectionFactory.createConnection();
//        //��������
//        conn.start();
//
//        //����Session���˷�����һ��������ʾ�Ự�Ƿ���������ִ�У��ڶ��������趨�Ự��Ӧ��ģʽ
//        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//        //��������
//        Destination dest = session.createQueue(SUBJECT);
//        //createTopic������������Topic
//        //session.createTopic("TOPIC");
//
//        //ͨ��session���Դ�����Ϣ��������
//        MessageProducer producer = session.createProducer(dest);
//        for (int i=0;i<100;i++) {
//            //��ʼ��һ��mq��Ϣ
//            TextMessage message = session.createTextMessage("hello active mq ����" + i);
//            //������Ϣ
//            producer.send(message);
//            LOGGER.debug("send message {}", i);
//        }
//
//        //�ر�mq����
//        conn.close();
//    }
//
//    public static void main(String[] args) throws JMSException {
//
//
//    }
//}
