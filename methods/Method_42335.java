/** 
 * ?????
 * @return ?????
 */
@Bean(name="orderQueryQueueDestination") public ActiveMQQueue orderQueryQueueDestination(){
  return new ActiveMQQueue(orderQueryQueueDestinationName);
}
