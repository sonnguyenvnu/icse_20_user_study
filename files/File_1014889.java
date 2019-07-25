package cn.timebusker;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfiguration {
	
	/**
	 * 任何�?��?到Fanout Exchange的消�?�都会被转�?�到与该Exchange绑定(Binding)的所有Queue上,�?需�?路由关键字匹�?
	 *  
	 * 1.这�?模�?需�?�??�?将Exchange与Queue进行绑定，一个Exchange�?�以绑定多个Queue，一个Queue�?�以�?�多个Exchange进行绑定
	 * 
	 * 2.这�?模�?�?需�?RouteKey
	 * 
	 * 3.如果接�?�到消�?�的Exchange没有与任何Queue绑定，则消�?�会被抛弃。
	 */
	
	public static final String FANOUT_ROUTING_KEY_TIMEBUSKER = "FANOUT.TIMEBUSKER";

	public static final String FANOUT_ROUTING_KEY_YUJIAOJIAO = "FANOUT.YUJIAOJIAO";

	public static final String FANOUT_ROUTING_KEY_MINE = "FANOUT.MINE";
	
	public static final String FANOUT_EXCHANGE = "FANOUT.EXCHANGE";
		
	@Bean("timebuskerf")
	public Queue timebusker() {
		return new Queue(FANOUT_ROUTING_KEY_TIMEBUSKER);
	}

	@Bean("yujiaojiaof")
	public Queue yujiaojiao() {
		return new Queue(FANOUT_ROUTING_KEY_YUJIAOJIAO);
	}

	@Bean("minef")
	public Queue mine() {
		return new Queue(FANOUT_ROUTING_KEY_MINE);
	}

	@Bean("fanoutExchange")
	FanoutExchange exchange() {
		return new FanoutExchange(FANOUT_EXCHANGE);
	}
	
    @Bean
    Binding bindingExchangeA(Queue timebuskerf,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(timebuskerf).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue yujiaojiaof, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(yujiaojiaof).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue minef, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(minef).to(fanoutExchange);
    }
	
}
