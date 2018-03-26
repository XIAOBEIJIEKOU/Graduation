package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.Seckill;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @Author Zhuang YeFan
 * @Date
 * @Description
 **/
public class RedisDao {

    private JedisPool jedisPool;

    private RuntimeSchema<Seckill> seckillRuntimeSchema = RuntimeSchema.createFrom(Seckill.class);

    public RedisDao(String ip, int port){
        jedisPool = new JedisPool(ip,port);
    }

    /**
     * ����seckillId �����Ʒ��Ϣ
     * @param seckillId
     * @return
     */
    public Seckill getSeckill(long seckillId){
        Jedis jedis = jedisPool.getResource();
        String key = "seckill:"+seckillId;
        //  redis��û��ʵ���ڲ����л�����byte[]�洢������������Ҫ�Զ��巴���л������Ӧ����Ķ���������
        byte[] bytes = jedis.get(key.getBytes());
        if (bytes != null){
            //����һ���µĿն���message����Ȼ��reids�����bytes���鷴���л���������յĶ���
            Seckill seckill = seckillRuntimeSchema.newMessage();
            ProtostuffIOUtil.mergeFrom(bytes, seckill, seckillRuntimeSchema);
            return seckill;
        }
        jedis.close();
        return null;
    }

    /**
     * �� Seckill ����ִ�����л���byte[] Ȼ��洢��redis��
     * @param seckill
     */
    public String putSeckill(Seckill seckill){
        Jedis jedis = jedisPool.getResource();
        String key = "seckill:"+seckill.getSeckillId();
        //  ��seckill������schema�����ģ��ת��byte[]��������Ҫ�������ӿ�Ч��
        byte[] bytes = ProtostuffIOUtil.toByteArray(seckill,seckillRuntimeSchema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        //  ������ʧЧʱ��Ļ��棬���ػ�����
        String result = jedis.setex(key.getBytes(),60 * 60,bytes);
        jedis.close();
        return result;
    }

    public void deleteSeckill(Seckill seckill){
        Jedis jedis = jedisPool.getResource();
        String key = "seckill:"+seckill.getSeckillId();
        jedis.del(key);
        jedis.close();
    }

    public boolean executeSeckill(Seckill seckill , long userPhone){
        Jedis jedis = jedisPool.getResource();
        Transaction transaction = jedis.multi();
        String recordKey = "seckillRecord:" + seckill.getSeckillId();
        //  ��¼������ϸ
        transaction.sadd(recordKey , String.valueOf(userPhone));
        //  �����
        int reduceStockNum = seckill.getNumber() - 1;
        seckill.setNumber(reduceStockNum);
        String reduceNumKey = "seckill:"+seckill.getSeckillId();
        byte[] bytes = ProtostuffIOUtil.toByteArray(seckill,seckillRuntimeSchema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        transaction.set(reduceNumKey.getBytes() , bytes);
        //  ����ִ��
        List<Object> list = transaction.exec();
        jedis.close();
        if (list != null){
            //���ظ� controller ��dto
            return true;
        }else{
            return false;
        }
    }


    /**
     * set������ɱ��¼
     * @param seckillId
     * @param userPhone
     * @return
     */
    public long saveSeckillRecord(long seckillId ,long userPhone){
        Jedis jedis = jedisPool.getResource();
        String key = "seckillRecord:" + seckillId;
        long result = jedis.sadd(key, String.valueOf(userPhone));
        jedis.close();
        return result;
    }

    /**
     * �����ڷ���true
     * @param seckillId
     * @param userPhone
     * @return
     */
    public boolean isRepeatSeckill(long seckillId , long userPhone){
        Jedis jedis = jedisPool.getResource();
        String key = "seckillRecord:" + seckillId;
        boolean flag = jedis.sismember(key, String.valueOf(userPhone));
        jedis.close();
        return flag;
    }

    public String getSeckillStock(long seckillId){
        Jedis jedis = jedisPool.getResource();
        String key = "seckill:"+ seckillId + "stock";
        String seckillStock = jedis.get(key);
        if (seckillStock != null){
            return seckillStock;
        }
        return null;
    }

    public String putSeckillStock(long seckillId, int stock){
        Jedis jedis = jedisPool.getResource();
        String key = "seckill:"+ seckillId + "stock";
        String result = jedis.set(key , String.valueOf(stock));
        return result;
    }
}
