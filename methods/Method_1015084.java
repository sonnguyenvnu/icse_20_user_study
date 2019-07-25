@Override public MessagePayload encode(){
  MessagePayload payload=new MessagePayload();
  payload.content=tip;
  return payload;
}
