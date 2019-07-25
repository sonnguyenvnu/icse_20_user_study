@Scheduled(fixedRate=5000) public void sendy(){
  String context=FanoutRabbitConfiguration.FANOUT_ROUTING_KEY_MINE + "\t";
  this.rabbitTemplate.convertAndSend(FanoutRabbitConfiguration.FANOUT_EXCHANGE,"",context);
}
