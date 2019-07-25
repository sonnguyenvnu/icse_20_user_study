@Override public MessagePayload encode(){
  MessagePayload payload=new MessagePayload();
  payload.searchableContent=content;
  payload.mentionedType=mentionedType;
  payload.mentionedTargets=mentionedTargets;
  return payload;
}
