@Override public MessagePayload encode(){
  MessagePayload payload=new MessagePayload();
  payload.content=type + "";
  return payload;
}
