package org.weicong.kafka;

import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Appender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.WriterAppender;

import ch.qos.logback.classic.html.HTMLLayout;

/**
 * @author weicong
 * @time   2020年12月23日
 * @version 1.0
 */
public class KafkaConsumerTest {
	
	
	private static final Logger LOGGER = LogManager.getLogger(KafkaConsumerTest.class);

    private static StringWriter stringWriter = new StringWriter();

//    public static void main(String[] args) {
//        createLogger();
//        int i = 1;
//        LOGGER.info("It is info log -  {}", i++);
//        LOGGER.warn("It is warn log - {} ", i);
//        LOGGER.error("It is error log");
//
//        System.out.println(stringWriter.toString());
//
//    }

//    private static void createLogger() {
//
//        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
//        final Configuration config = ctx.getConfiguration();
//
//        PatternLayout layout = PatternLayout.newBuilder()
//                .withPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} %level [%t] [%c] [%M] [%l] - %msg%n").build();
//
//        WriterAppender writerAppender = WriterAppender.newBuilder().setName("writeLogger").setTarget(stringWriter)
//                .setLayout(layout).build();
//        writerAppender.start();
//        config.addAppender(writerAppender);
//
//        AppenderRef ref = AppenderRef.createAppenderRef("writeLogger", null, null);
//        AppenderRef[] refs = new AppenderRef[] { ref };
//
//        LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.INFO, "example", null, refs, null, config,
//                null);
//
//        loggerConfig.addAppender(writerAppender, null, null);
//        config.addLogger("example", loggerConfig);
//        ctx.updateLoggers();
//    }
	
	
//	private static void name() {
//		Logger l = Logger.getLogger( ...  );
//		 StringWriter writer = new StringWriter();
//		 WriterAppender appender = new WriterAppender( new HTMLLayout(), writer );
//		 l.addAppender( appender );
//		    ... run code here
//		  writer.flush();
//		 l.removeAppender( appender );
//		 return writer.toString()
//	}
	

	public static void main(String[] args) {
		
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093"); // kafka集群地址，bootstrap.servers 多个
        props.put("group.id", "g2"); // 消费者的组id
        props.put("enable.auto.commit", "true"); // 自动提交偏移量
        props.put("auto.commit.interval.ms", "1000"); // 上一个为true时提交的频率
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // x 暂不配置
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // x 暂不配置 
        
        //配置文件设置sasl_plaintext认证
        
        //可以通过system.setProperty来指定验证文件，可以作为调试将环境使用，正式环境可以使用-Djava.security.auth.login.config来指定
//        System.setProperty("java.security.auth.login.config","D:\\0-important\\kafka_2.12-2.7.0\\kafka_client_jaas.conf");
        
        // 非文件方式实现 plain 认证
        props.put("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"alice\" password=\"alice-secret\";");
        
        props.put("security.protocol","SASL_PLAINTEXT");
        props.put("sasl.mechanism","PLAIN");
        
//        ConsumerConfig        
//        props.put("fetch.min.bytes", ""); // 消费者从服务器获取记录的最小字节数
//        props.put("fetch.max.wait.ms", ""); // 指定 broker 的等待时间，默认是 500 毫秒
//        props.put("max.partition.fetch.bytes", ""); // 服务器从每个分区里返回给消费者的最大字节数。它的默认值时 1MB
//        props.put("session.timeout.ms", ""); //  消费者在被认为死亡之前可以与服务器断开连接的时间，默认是 3s
//        props.put("auto.offset.reset", ""); // 消费者在读取一个没有偏移量的分区或者偏移量无效的情况下的该如何处理，latest和earliest
//        props.put("partition.assignment.strategy", ""); // 分区分配策略 Range 和 RoundRobin x 暂不配置
//        props.put("client.id", ""); // 任意字符串，broker 用他来标识从客户端发送过来的消息
//        props.put("max.poll.records", ""); // 控制单次调用 call() 方法能够返回的记录数量
        // receive.buffer.bytes 和 send.buffer.bytes // socket 在读写数据时用到的 TCP 缓冲区也可以设置大小  x 暂不配置
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        
        // 订阅主题列表 topic 
//        consumer.subscribe(Arrays.asList("test01","mytopic"));
        consumer.subscribe(Arrays.asList("test2"));
        // 可指定分区 Partition 
//        String topic = "testtopic";
//        TopicPartition partition0 = new TopicPartition(topic, 0);
//        TopicPartition partition1 = new TopicPartition(topic, 1);
//        consumer.assign(Arrays.asList(partition1));

        while (true) {
        	try {
        		ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1500));
        		System.err.println(records.isEmpty());
        		for (ConsumerRecord<String, String> record : records) {
        			// key value 分区 主题 时间戳 x 时间戳类型: CreateTime 和 LogAppendTime 头部 x 偏移 x
        			System.out.printf("offset=%d, key=%s, value=%s, partition=%s, timestamptype=%s, topic=%s, time=%s", 
        					record.offset(), record.key(), record.value(), record.partition(), record.timestampType(), record.topic(), record.timestamp() +"\n");
        		}
        		Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
				consumer.close();
				break;
			} 
        }
	}
}
