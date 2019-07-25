private static Message prepare(MessageKind kind,String text,@Nullable SNode node){
  Message message=new Message(kind,text);
  if (node != null) {
    message.setHintObject(node.getReference());
  }
  return message;
}
