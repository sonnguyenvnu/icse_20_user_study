@Override public void process(JanusGraphVertex vertex,ScanMetrics metrics){
  try {
    BackendTransaction mutator=writeTx.getTxHandle();
    if (index instanceof RelationTypeIndex) {
      RelationTypeIndexWrapper wrapper=(RelationTypeIndexWrapper)index;
      InternalRelationType wrappedType=wrapper.getWrappedType();
      EdgeSerializer edgeSerializer=writeTx.getEdgeSerializer();
      List<Entry> additions=new ArrayList<>();
      for (      Object relation : vertex.query().types(indexRelationTypeName).direction(Direction.OUT).relations()) {
        InternalRelation janusgraphRelation=(InternalRelation)relation;
        for (int pos=0; pos < janusgraphRelation.getArity(); pos++) {
          if (!wrappedType.isUnidirected(Direction.BOTH) && !wrappedType.isUnidirected(EdgeDirection.fromPosition(pos)))           continue;
          Entry entry=edgeSerializer.writeRelation(janusgraphRelation,wrappedType,pos,writeTx);
          additions.add(entry);
        }
      }
      StaticBuffer vertexKey=writeTx.getIdInspector().getKey(vertex.longId());
      mutator.mutateEdges(vertexKey,additions,KCVSCache.NO_DELETIONS);
      metrics.incrementCustom(ADDED_RECORDS_COUNT,additions.size());
    }
 else     if (index instanceof JanusGraphIndex) {
      IndexType indexType=managementSystem.getSchemaVertex(index).asIndexType();
      assert indexType != null;
      IndexSerializer indexSerializer=graph.getIndexSerializer();
      List<JanusGraphElement> elements;
switch (indexType.getElement()) {
case VERTEX:
        elements=ImmutableList.of(vertex);
      break;
case PROPERTY:
    elements=Lists.newArrayList();
  for (  JanusGraphVertexProperty p : addIndexSchemaConstraint(vertex.query(),indexType).properties()) {
    elements.add(p);
  }
break;
case EDGE:
elements=Lists.newArrayList();
for (Object e : addIndexSchemaConstraint(vertex.query().direction(Direction.OUT),indexType).edges()) {
elements.add((JanusGraphEdge)e);
}
break;
default :
throw new AssertionError("Unexpected category: " + indexType.getElement());
}
if (indexType.isCompositeIndex()) {
for (JanusGraphElement element : elements) {
Set<IndexSerializer.IndexUpdate<StaticBuffer,Entry>> updates=indexSerializer.reindexElement(element,(CompositeIndexType)indexType);
for (IndexSerializer.IndexUpdate<StaticBuffer,Entry> update : updates) {
log.debug("Mutating index {}: {}",indexType,update.getEntry());
mutator.mutateIndex(update.getKey(),Lists.newArrayList(update.getEntry()),KCVSCache.NO_DELETIONS);
metrics.incrementCustom(ADDED_RECORDS_COUNT);
}
}
}
 else {
assert indexType.isMixedIndex();
Map<String,Map<String,List<IndexEntry>>> documentsPerStore=new HashMap<>();
for (JanusGraphElement element : elements) {
indexSerializer.reindexElement(element,(MixedIndexType)indexType,documentsPerStore);
metrics.incrementCustom(DOCUMENT_UPDATES_COUNT);
}
mutator.getIndexTransaction(indexType.getBackingIndexName()).restore(documentsPerStore);
}
}
 else throw new UnsupportedOperationException("Unsupported index found: " + index);
}
 catch (final Exception e) {
managementSystem.rollback();
writeTx.rollback();
metrics.incrementCustom(FAILED_TX);
throw new JanusGraphException(e.getMessage(),e);
}
}
