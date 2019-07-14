private PartitionVertexAggregate<M> getPartitioned(long vertexId){
  assert idManager.isPartitionedVertex(vertexId);
  vertexId=getCanonicalId(vertexId);
  PartitionVertexAggregate<M> state=partitionVertices.get(vertexId);
  if (state == null) {
    partitionVertices.putIfAbsent(vertexId,new PartitionVertexAggregate<>(previousScopes));
    state=partitionVertices.get(vertexId);
  }
  return state;
}
