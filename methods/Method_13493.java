public <T>void sendTransactionalMsg(T msg,int num) throws Exception {
  MessageBuilder builder=MessageBuilder.withPayload(msg).setHeader(MessageHeaders.CONTENT_TYPE,MimeTypeUtils.APPLICATION_JSON);
  builder.setHeader("test",String.valueOf(num));
  builder.setHeader(RocketMQHeaders.TAGS,"binder");
  Message message=builder.build();
  source.output2().send(message);
}
