public Map<Long,EntryList> retrievePartitionAggregates(){
  for (  PartitionVertexAggregate agg : partitionVertices.values())   agg.completeIteration();
  return Maps.transformValues(partitionVertices,PartitionVertexAggregate::getLoadedProperties);
}
