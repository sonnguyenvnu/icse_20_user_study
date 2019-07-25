@Override public void decode(MessagePayload payload){
  super.decode(payload);
  name=payload.searchableContent;
  size=Integer.parseInt(payload.content);
}
