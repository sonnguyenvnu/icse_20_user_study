@Override public void process(StaticBuffer key,Map<SliceQuery,EntryList> entries,ScanMetrics metrics){
  long vertexId=getVertexId(key);
  assert entries.size() == 1;
  assert entries.get(everythingQueryLimit) != null;
  final EntryList everything=entries.get(everythingQueryLimit);
  if (!isGhostVertex(vertexId,everything)) {
    return;
  }
  if (everything.size() >= RELATION_COUNT_LIMIT) {
    metrics.incrementCustom(SKIPPED_GHOST_LIMIT_COUNT);
    return;
  }
  JanusGraphVertex vertex=tx.getInternalVertex(vertexId);
  Preconditions.checkArgument(vertex instanceof CacheVertex,"The bounding transaction is not configured correctly");
  CacheVertex v=(CacheVertex)vertex;
  v.loadRelations(EVERYTHING_QUERY,input -> everything);
  int removedRelations=0;
  Iterator<JanusGraphRelation> iterator=v.query().noPartitionRestriction().relations().iterator();
  while (iterator.hasNext()) {
    iterator.next();
    iterator.remove();
    removedRelations++;
  }
  metrics.incrementCustom(REMOVED_VERTEX_COUNT);
  metrics.incrementCustom(REMOVED_RELATION_COUNT,removedRelations);
}
