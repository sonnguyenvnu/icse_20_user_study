public InternalVertex[] getAllRepresentatives(JanusGraphVertex partitionedVertex,boolean restrict2Partitions){
  Preconditions.checkArgument(isPartitionedVertex(partitionedVertex));
  long[] ids;
  if (!restrict2Partitions || !config.hasRestrictedPartitions()) {
    ids=idManager.getPartitionedVertexRepresentatives(partitionedVertex.longId());
  }
 else {
    int[] restrictedPartitions=config.getRestrictedPartitions();
    ids=new long[restrictedPartitions.length];
    for (int i=0; i < ids.length; i++) {
      ids[i]=idManager.getPartitionedVertexId(partitionedVertex.longId(),restrictedPartitions[i]);
    }
  }
  Preconditions.checkArgument(ids.length > 0);
  InternalVertex[] vertices=new InternalVertex[ids.length];
  for (int i=0; i < ids.length; i++)   vertices[i]=getExistingVertex(ids[i]);
  return vertices;
}
