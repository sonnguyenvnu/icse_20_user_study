private void clearDictCache(String dictId){
  eventPublisher.publishEvent(new ClearDictionaryCacheEvent(dictId));
}
