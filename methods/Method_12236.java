@RabbitListener(queues=MQConfig.MIAOSHATEST) public void receiveMiaoShaMessage(Message message,Channel channel) throws IOException {
  log.info("???????:{}",message);
  String messRegister=new String(message.getBody(),"UTF-8");
  channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
  MiaoShaMessageVo msm=RedisService.stringToBean(messRegister,MiaoShaMessageVo.class);
  messageService.insertMs(msm);
}
