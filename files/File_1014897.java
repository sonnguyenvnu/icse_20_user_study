package cn.timebusker;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfiguration {

	/**
	 * Topic交�?�机
	 * 
	 * 对Key进行模�?匹�?�?�进行投递，�?�以使用符�?�＃匹�?一个或多个�?，符�?�＊匹�?正好 一 个�?。
	 * 比如，abc.#匹�?abc.def.ghi,abc.*�?�匹�?abc.def；
	 * 
	 * 任何�?��?到Topic Exchange的消�?�都会被转�?�到所有关心RouteKey中指定�?题的Queue上
	 * 
	 * 1. 这�?模�?需�?RouteKey，�?�??�?绑定Exchange与Queue。
	 * 
	 * 2.如果Exchange没有�?�现能够与RouteKey匹�?的Queue，则会抛弃此消�?�。
	 * 
	 * 3. 在进行绑定时，�?�??供一个该队列关心的主题，如“#.log.#�?表示该队列关心所有涉�?�log的消�?�(一个RouteKey为�?MQ.log.
	 * error�?的消�?�会被转�?�到该队列)。
	 * 
	 * 4. “#�?表示0个或若干个关键字，“*�?表示一个关键字。如“log.*�?能与“log.warn�?匹�?，无法与“log.warn.timeout�?
	 * 匹�?；但是“log.#�?能与上述两者匹�?。
	 * 
	 */

	public static final String TOPIC_ROUTING_KEY_TIMEBUSKER = "TOPIC.TIMEBUSKER";

	public static final String TOPIC_ROUTING_KEY_YUJIAOJIAO = "TOPIC.YUJIAOJIAO";

	public static final String TOPIC_ROUTING_KEY_MINE = "TOPIC.MINE";
	
	public static final String TOPIC_EXCHANGE = "TOPIC.EXCHANGE";

	@Bean("timebuskert")
	public Queue timebusker() {
		return new Queue(TOPIC_ROUTING_KEY_TIMEBUSKER);
	}

	@Bean("yujiaojiaot")
	public Queue yujiaojiao() {
		return new Queue(TOPIC_ROUTING_KEY_YUJIAOJIAO);
	}

	@Bean("minet")
	public Queue mine() {
		return new Queue(TOPIC_ROUTING_KEY_MINE);
	}

	@Bean("topicExchange")
	TopicExchange exchange() {
		return new TopicExchange(TOPIC_EXCHANGE);
	}
	
	/**
	 * 进入TOPIC_EXCHANGE消�?�交�?�机的消�?�，会根�?�route key与"TOPIC.*"进行匹�?，路由到消�?�队列中
	 */
	@Bean
	Binding bindingExchange1(Queue yujiaojiaot, TopicExchange exchange) {
		return BindingBuilder.bind(yujiaojiaot).to(exchange).with("TOPIC.*");
	}
	
	@Bean
	Binding bindingExchange2(Queue timebuskert, TopicExchange exchange) {
		return BindingBuilder.bind(timebuskert).to(exchange).with("TOPIC.*");
	}
	
	@Bean
	Binding bindingExchange3(Queue minet, TopicExchange topicExchange) {
		return BindingBuilder.bind(minet).to(topicExchange).with("TOPIC.*");
	}
}
