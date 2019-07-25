package cn.timebusker;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 简�?�定义消�?�队列
 */
@Configuration
public class DirectRabbitConfiguration {

	/**
	 * Direct:RabbitMQ默认的 Exchange(消�?�交�?�机):�?�需指定消�?�队列，消�?�直接通过默认的Direct Exchange消�?�交�?�机进行转�?�（点-点）
	 * 
	 * 1. 消�?�传递时需�?一个“RouteKey�?，�?�以简�?�的�?�解为�?�?��?到的队列�??字。
	 *  任何�?��?到Direct Exchange的消�?�都会被转�?�到RouteKey中指定的Queue。
	 * 
	 * 2.如果vhost中�?存在RouteKey中指定的队列�??，则该消�?�会被抛弃。
	 * 
	 * 3.所谓的路由关键字 RouteKey ，�?�以�?�解为 消�?�队列的�??称 Queue
	 */
	
	public static final String DIRECT_ROUTING_KEY_TIMEBUSKER = "DIRECT.TIMEBUSKER";

	public static final String DIRECT_ROUTING_KEY_YUJIAOJIAO = "DIRECT.YUJIAOJIAO";

	public static final String DIRECT_ROUTING_KEY_OBJECT = "DIRECT.OBJECT";

	/**
	 * 必须设置消�?�队列
	 * 队列�??称需�?与路由关键字相匹�?
	 */
	@Bean("timebuskerd")
	public Queue timebusker() {
		return new Queue(DIRECT_ROUTING_KEY_TIMEBUSKER);
	}

	@Bean("yujiaojiaod")
	public Queue yujiaojiao() {
		return new Queue(DIRECT_ROUTING_KEY_YUJIAOJIAO);
	}

	@Bean("objectd")
	public Queue object() {
		return new Queue(DIRECT_ROUTING_KEY_OBJECT);
	}
}
