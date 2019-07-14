@Override public MessageContent readFor(final Request request){
  return MessageContent.content().withContent(toJson(pojo)).build();
}
