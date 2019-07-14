/** 
 * ?????
 * @return ?????
 */
@Bean(name="tradeQueueDestination") public ActiveMQQueue tradeQueueDestination(){
  return new ActiveMQQueue(tradeQueueDestinationName);
}
