public <T>void sendWithTags(T msg,String tag) throws Exception {
  Message message=MessageBuilder.createMessage(msg,new MessageHeaders(Stream.of(tag).collect(Collectors.toMap(str -> MessageConst.PROPERTY_TAGS,String::toString))));
  source.output1().send(message);
}
