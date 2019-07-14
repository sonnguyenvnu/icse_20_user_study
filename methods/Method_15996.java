protected boolean pushCreatedEvent(){
  return RecordCreationEntity.class.isAssignableFrom(entityType);
}
