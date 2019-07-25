@Override public void run(String... args) throws Exception {
  for (int i=0; i < 5; i++) {
    try {
      rocketMQTemplate.convertAndSend("test-topic-1","Hello, World!");
      System.out.println("Send OK!");
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
  rocketMQTemplate.send("test-topic-1",MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
  rocketMQTemplate.syncSend("test-topic-1","Hello, World! I'm from simple message");
  rocketMQTemplate.convertAndSend("test-topic-2",new OrderPaidEvent("T_001",new BigDecimal("88.00")));
  rocketMQTemplate.sendDelayed("test-topic-1","I'm delayed message",MessageDelayLevel.TIME_1M);
  rocketMQTemplate.sendOneWay("test-topic-1",MessageBuilder.withPayload("I'm one way message").build());
  rocketMQTemplate.syncSendOrderly("test-topic-4","I'm order message","1234");
  rocketMQTemplate.asyncSend("test-topic-1",MessageBuilder.withPayload("I'm async message").build(),new SendCallback(){
    @Override public void onSuccess(    SendResult sendResult){
      log.info("async: {}",sendResult);
    }
    @Override public void onException(    Throwable e){
      log.error(e.getMessage(),e);
    }
  }
);
  System.out.println("send finished!");
}
