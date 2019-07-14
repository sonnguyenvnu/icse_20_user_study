private static MixedIndexType getMixedIndex(String indexName,StandardJanusGraphTx transaction){
  final IndexType index=ManagementSystem.getGraphIndexDirect(indexName,transaction);
  Preconditions.checkArgument(index != null,"Index with name [%s] is unknown or not configured properly",indexName);
  Preconditions.checkArgument(index.isMixedIndex());
  return (MixedIndexType)index;
}
