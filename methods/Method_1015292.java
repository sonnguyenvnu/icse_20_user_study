public <T extends BaseModel>T get(Class<T> type,long id){
  return entityManager.find(type,id);
}
