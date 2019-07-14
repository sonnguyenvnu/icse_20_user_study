public boolean isPartitionedVertex(long id){
  return isUserVertexId(id) && VertexIDType.PartitionedVertex.is(id);
}
