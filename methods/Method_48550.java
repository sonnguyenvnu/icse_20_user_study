private Map<String,List<IndexEntry>> getDocuments(Map<String,Map<String,List<IndexEntry>>> documentsPerStore,MixedIndexType index){
  return documentsPerStore.computeIfAbsent(index.getStoreName(),k -> Maps.newHashMap());
}
