protected boolean isGhostVertex(long vertexId,EntryList firstEntries){
  if (idManager.isPartitionedVertex(vertexId) && !idManager.isCanonicalVertexId(vertexId))   return false;
  RelationCache relCache=tx.getEdgeSerializer().parseRelation(firstEntries.get(0),true,tx);
  return relCache.typeId != BaseKey.VertexExists.longId();
}
