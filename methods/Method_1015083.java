@Override public void decode(MessagePayload payload){
  operatorId=payload.content;
  messageUid=Long.parseLong(new String(payload.binaryContent));
}
