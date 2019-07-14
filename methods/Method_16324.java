@Override public DictionaryEntity deleteByPk(String id){
  eventPublisher.publishEvent(new ClearDictionaryCacheEvent(id));
  return super.deleteByPk(id);
}
