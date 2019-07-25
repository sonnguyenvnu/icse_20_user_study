package com.zhisheng.connectors.mysql.utils;

import com.zhisheng.common.utils.GsonUtil;
import com.zhisheng.connectors.mysql.model.Student;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Desc: å¾€kafkaä¸­å†™æ•°æ?®,å?¯ä»¥ä½¿ç”¨è¿™ä¸ªmainå‡½æ•°è¿›è¡Œæµ‹è¯•
 * Created by zhisheng on 2019-02-17
 * Blog: http://www.54tianzhisheng.cn/2019/01/09/Flink-MySQL-sink/
 */
public class KafkaUtil {
    public static final String broker_list = "localhost:9092";
    public static final String topic = "student";  //kafka topic éœ€è¦?å’Œ flink ç¨‹åº?ç”¨å?Œä¸€ä¸ª topic

    public static void writeToKafka() throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", broker_list);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer = new KafkaProducer<String, String>(props);

        for (int i = 1; i <= 100; i++) {
            Student student = new Student(i, "zhisheng" + i, "password" + i, 18 + i);
            ProducerRecord record = new ProducerRecord<String, String>(topic, null, null, GsonUtil.toJson(student));
            producer.send(record);
            System.out.println("å?‘é€?æ•°æ?®: " + GsonUtil.toJson(student));
            Thread.sleep(10 * 1000); //å?‘é€?ä¸€æ?¡æ•°æ?® sleep 10sï¼Œç›¸å½“äºŽ 1 åˆ†é’Ÿ 6 æ?¡
        }
        producer.flush();
    }

    public static void main(String[] args) throws InterruptedException {
        writeToKafka();
    }
}
