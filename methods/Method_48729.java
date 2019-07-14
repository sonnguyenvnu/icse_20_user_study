public long[] getPartitionedVertexRepresentatives(long partitionedVertexId){
  Preconditions.checkArgument(isPartitionedVertex(partitionedVertexId));
  assert getPartitionBound() < Integer.MAX_VALUE;
  long[] ids=new long[(int)getPartitionBound()];
  for (int i=0; i < getPartitionBound(); i++) {
    ids[i]=getPartitionedVertexId(partitionedVertexId,i);
  }
  return ids;
}
