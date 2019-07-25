/** 
 * ????????????????????
 * @param obj
 */
@RabbitHandler @RabbitListener(queues=DirectRabbitConfiguration.DIRECT_ROUTING_KEY_TIMEBUSKER) public void processt(Object obj){
  System.out.println("T\t   Receiver  : " + obj.toString());
}
