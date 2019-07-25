@Override public void decode(MessagePayload payload){
  content=payload.searchableContent;
  mentionedType=payload.mentionedType;
  mentionedTargets=payload.mentionedTargets;
}
