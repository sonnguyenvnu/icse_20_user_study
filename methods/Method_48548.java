private IndexUpdate<String,IndexEntry> getMixedIndexUpdate(JanusGraphElement element,PropertyKey key,Object value,MixedIndexType index,IndexUpdate.Type updateType){
  return new IndexUpdate<>(index,updateType,element2String(element),new IndexEntry(key2Field(index.getField(key)),value),element);
}
