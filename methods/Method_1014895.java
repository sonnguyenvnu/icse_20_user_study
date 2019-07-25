@Scheduled(fixedRate=5000) public void sendo(){
  String context=FanoutRabbitConfiguration.FANOUT_ROUTING_KEY_YUJIAOJIAO + "\t";
  this.rabbitTemplate.convertAndSend(FanoutRabbitConfiguration.FANOUT_EXCHANGE,"",context);
}
