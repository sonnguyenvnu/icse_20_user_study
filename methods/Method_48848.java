@Override protected void validateIndexStatus(){
  if (!(index instanceof RelationTypeIndex || index instanceof JanusGraphIndex)) {
    throw new UnsupportedOperationException("Unsupported index found: " + index);
  }
  if (index instanceof JanusGraphIndex) {
    JanusGraphIndex graphIndex=(JanusGraphIndex)index;
    if (graphIndex.isMixedIndex())     throw new UnsupportedOperationException("Cannot remove mixed indexes through JanusGraph. This can " + "only be accomplished in the indexing system directly.");
    CompositeIndexType indexType=(CompositeIndexType)managementSystem.getSchemaVertex(index).asIndexType();
    graphIndexId=indexType.getID();
  }
  JanusGraphSchemaVertex schemaVertex=managementSystem.getSchemaVertex(index);
  SchemaStatus actualStatus=schemaVertex.getStatus();
  Preconditions.checkArgument(actualStatus == SchemaStatus.DISABLED,"The index [%s] must be disabled before it can be removed",indexName);
}
