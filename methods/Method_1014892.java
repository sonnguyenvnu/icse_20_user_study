@RabbitHandler @RabbitListener(queues=DirectRabbitConfiguration.DIRECT_ROUTING_KEY_OBJECT) public void processo(MessageEntity message){
  System.out.println("O\t    Receiver  : " + message.toString());
}
