private Message merge(Message message){
  List<Object> sources=new ArrayList<>();
  sources.addAll(getSources());
  sources.addAll(message.getSources());
  return new Message(sources,message.getMessage(),message.getCause());
}
