public void reindexElement(JanusGraphElement element,MixedIndexType index,Map<String,Map<String,List<IndexEntry>>> documentsPerStore){
  if (!indexAppliesTo(index,element))   return;
  final List<IndexEntry> entries=Lists.newArrayList();
  for (  final ParameterIndexField field : index.getFieldKeys()) {
    final PropertyKey key=field.getFieldKey();
    if (field.getStatus() == SchemaStatus.DISABLED)     continue;
    if (element.properties(key.name()).hasNext()) {
      element.values(key.name()).forEachRemaining(value -> entries.add(new IndexEntry(key2Field(field),value)));
    }
  }
  final Map<String,List<IndexEntry>> documents=documentsPerStore.computeIfAbsent(index.getStoreName(),k -> Maps.newHashMap());
  getDocuments(documentsPerStore,index).put(element2String(element),entries);
}
