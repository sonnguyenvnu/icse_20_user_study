public Set<IndexUpdate<StaticBuffer,Entry>> reindexElement(JanusGraphElement element,CompositeIndexType index){
  final Set<IndexUpdate<StaticBuffer,Entry>> indexEntries=Sets.newHashSet();
  if (!indexAppliesTo(index,element))   return indexEntries;
  Iterable<RecordEntry[]> records;
  if (element instanceof JanusGraphVertex)   records=indexMatches((JanusGraphVertex)element,index);
 else {
    assert element instanceof JanusGraphRelation;
    records=Collections.EMPTY_LIST;
    final RecordEntry[] record=indexMatch((JanusGraphRelation)element,index);
    if (record != null)     records=ImmutableList.of(record);
  }
  for (  final RecordEntry[] record : records) {
    indexEntries.add(new IndexUpdate<>(index,IndexUpdate.Type.ADD,getIndexKey(index,record),getIndexEntry(index,record,element),element));
  }
  return indexEntries;
}
