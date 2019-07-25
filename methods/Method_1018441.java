@RabbitListener(queues=RabbitConstants.QUEUE_NAME_DEAD_QUEUE) public void process(SendMessage sendMessage,Channel channel,Message message) throws Exception {
  logger.info("[{}]???????????????????{}",RabbitConstants.QUEUE_NAME_SEND_COUPON,JSON.toJSONString(sendMessage));
  System.out.println(message.getMessageProperties().getDeliveryTag());
  try {
    Assert.notNull(sendMessage,"sendMessage ??????NULL");
    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
  }
 catch (  Exception e) {
    logger.error("MQ??????????:{}",message.getMessageProperties().getCorrelationIdString(),JSON.toJSONString(sendMessage),e);
    try {
      channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
 catch (    Exception dbe) {
      logger.error("????MQ????????????????????{}",JSON.toJSONString(sendMessage),dbe);
      channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
    }
  }
}
