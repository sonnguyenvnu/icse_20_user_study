@Scheduled(fixedRate=1000000) public void sendo(){
  MessageEntity message=new MessageEntity();
  this.rabbitTemplate.convertAndSend(DirectRabbitConfiguration.DIRECT_ROUTING_KEY_OBJECT,message);
}
