@Override public int updateByPk(String id,DictionaryEntity entity){
  eventPublisher.publishEvent(new ClearDictionaryCacheEvent(id));
  return super.updateByPk(id,entity);
}
