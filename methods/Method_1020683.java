public Update update(ObjectId id){
  if (id == null) {
    throw new IllegalArgumentException("Object id must not be null");
  }
  return update("{_id:#}",id);
}
