public static IndexRecords indexMatches(JanusGraphVertex vertex,CompositeIndexType index,PropertyKey replaceKey,Object replaceValue){
  final IndexRecords matches=new IndexRecords();
  final IndexField[] fields=index.getFieldKeys();
  if (indexAppliesTo(index,vertex)) {
    indexMatches(vertex,new RecordEntry[fields.length],matches,fields,0,false,replaceKey,new RecordEntry(0,replaceValue,replaceKey));
  }
  return matches;
}
