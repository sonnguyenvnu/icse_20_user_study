public boolean exists(String templateEngineKind,String documentKind){
  initializeIfNeeded();
  String key=getKey(templateEngineKind,documentKind);
  return templateEnginesCache.containsKey(key);
}
