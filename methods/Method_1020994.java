@Override public Action consume(Message message,ConsumeContext context){
  byte[] bytes=message.getBody();
  Object object=getSerializer().deserialize(bytes);
  notifyListeners(message.getTopic(),object);
  return Action.CommitMessage;
}
