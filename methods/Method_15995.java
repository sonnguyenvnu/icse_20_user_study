protected boolean pushModifyEvent(){
  return RecordModifierEntity.class.isAssignableFrom(entityType);
}
