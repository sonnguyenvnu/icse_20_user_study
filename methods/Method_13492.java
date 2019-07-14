public <T>void sendObject(T msg,String tag) throws Exception {
  Message message=MessageBuilder.withPayload(msg).setHeader(MessageConst.PROPERTY_TAGS,tag).setHeader(MessageHeaders.CONTENT_TYPE,MimeTypeUtils.APPLICATION_JSON).build();
  source.output1().send(message);
}
