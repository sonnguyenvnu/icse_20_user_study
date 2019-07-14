private static IndexRecords indexMatches(JanusGraphVertex vertex,CompositeIndexType index,boolean onlyLoaded,PropertyKey replaceKey,RecordEntry replaceValue){
  final IndexRecords matches=new IndexRecords();
  final IndexField[] fields=index.getFieldKeys();
  indexMatches(vertex,new RecordEntry[fields.length],matches,fields,0,onlyLoaded,replaceKey,replaceValue);
  return matches;
}
