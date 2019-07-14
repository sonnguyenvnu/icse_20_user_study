private static void indexMatches(JanusGraphVertex vertex,RecordEntry[] current,IndexRecords matches,IndexField[] fields,int pos,boolean onlyLoaded,PropertyKey replaceKey,RecordEntry replaceValue){
  if (pos >= fields.length) {
    matches.add(current);
    return;
  }
  final PropertyKey key=fields[pos].getFieldKey();
  List<RecordEntry> values;
  if (key.equals(replaceKey)) {
    values=ImmutableList.of(replaceValue);
  }
 else {
    values=new ArrayList<>();
    Iterable<JanusGraphVertexProperty> props;
    if (onlyLoaded || (!vertex.isNew() && IDManager.VertexIDType.PartitionedVertex.is(vertex.longId()))) {
      final VertexCentricQueryBuilder qb=((InternalVertex)vertex).tx().query(vertex);
      qb.noPartitionRestriction().type(key);
      if (onlyLoaded)       qb.queryOnlyLoaded();
      props=qb.properties();
    }
 else {
      props=vertex.query().keys(key.name()).properties();
    }
    for (    final JanusGraphVertexProperty p : props) {
      assert !onlyLoaded || p.isLoaded() || p.isRemoved();
      assert key.dataType().equals(p.value().getClass()) : key + " -> " + p;
      values.add(new RecordEntry(p));
    }
  }
  for (  final RecordEntry value : values) {
    current[pos]=value;
    indexMatches(vertex,current,matches,fields,pos + 1,onlyLoaded,replaceKey,replaceValue);
  }
}
