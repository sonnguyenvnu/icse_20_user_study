public long getPartitionedVertexId(long partitionedVertexId,long otherPartition){
  Preconditions.checkArgument(VertexIDType.PartitionedVertex.is(partitionedVertexId));
  long count=partitionedVertexId >>> (partitionBits + USERVERTEX_PADDING_BITWIDTH);
  assert count > 0;
  return constructId(count,otherPartition,VertexIDType.PartitionedVertex);
}
