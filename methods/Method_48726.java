private long getCanonicalVertexIdFromCount(long count){
  long partition=getPartitionHashForId(count);
  return constructId(count,partition,VertexIDType.PartitionedVertex);
}
