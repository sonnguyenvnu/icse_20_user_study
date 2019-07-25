private Assignment forge(SourceRHS sourceRHS,Type sourceType,Type targetType){
  Assignment assignment=super.forgeMapping(sourceRHS,sourceType,targetType);
  if (assignment != null) {
    ctx.getMessager().note(2,Message.ITERABLEMAPPING_CREATE_ELEMENT_NOTE,assignment);
  }
  return assignment;
}
