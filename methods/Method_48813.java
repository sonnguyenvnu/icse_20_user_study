public long getCanonicalId(long vertexId){
  if (!idManager.isPartitionedVertex(vertexId))   return vertexId;
 else   return idManager.getCanonicalVertexId(vertexId);
}
