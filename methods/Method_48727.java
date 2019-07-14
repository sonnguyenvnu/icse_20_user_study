public long getCanonicalVertexId(long partitionedVertexId){
  Preconditions.checkArgument(VertexIDType.PartitionedVertex.is(partitionedVertexId));
  long count=partitionedVertexId >>> (partitionBits + USERVERTEX_PADDING_BITWIDTH);
  return getCanonicalVertexIdFromCount(count);
}
