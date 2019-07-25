public static Instance create(InstanceId id){
  Assert.notNull(id,"'id' must not be null");
  return new Instance(id);
}
