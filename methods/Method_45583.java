/** 
 * ???ConsumerConfig??
 * @param consumerConfig the consumer config
 */
public static void invalidateConsumerConfig(ConsumerBootstrap consumerConfig){
  REFERRED_CONSUMER_CONFIGS.remove(consumerConfig);
}
