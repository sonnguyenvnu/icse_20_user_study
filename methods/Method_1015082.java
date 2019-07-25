@Override public MessagePayload encode(){
  MessagePayload payload=new MessagePayload();
  payload.content=operatorId;
  payload.binaryContent=new StringBuffer().append(messageUid).toString().getBytes();
  return payload;
}
