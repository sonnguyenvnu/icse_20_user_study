private JanusGraphIndex createMixedIndex(String indexName,ElementCategory elementCategory,JanusGraphSchemaType constraint,String backingIndex){
  Preconditions.checkArgument(graph.getIndexSerializer().containsIndex(backingIndex),"Unknown external index backend: %s",backingIndex);
  checkIndexName(indexName);
  TypeDefinitionMap def=new TypeDefinitionMap();
  def.setValue(TypeDefinitionCategory.INTERNAL_INDEX,false);
  def.setValue(TypeDefinitionCategory.ELEMENT_CATEGORY,elementCategory);
  def.setValue(TypeDefinitionCategory.BACKING_INDEX,backingIndex);
  def.setValue(TypeDefinitionCategory.INDEXSTORE_NAME,indexName);
  def.setValue(TypeDefinitionCategory.INDEX_CARDINALITY,Cardinality.LIST);
  def.setValue(TypeDefinitionCategory.STATUS,SchemaStatus.ENABLED);
  JanusGraphSchemaVertex indexVertex=transaction.makeSchemaVertex(JanusGraphSchemaCategory.GRAPHINDEX,indexName,def);
  Preconditions.checkArgument(constraint == null || (elementCategory.isValidConstraint(constraint) && constraint instanceof JanusGraphSchemaVertex));
  if (constraint != null) {
    addSchemaEdge(indexVertex,(JanusGraphSchemaVertex)constraint,TypeDefinitionCategory.INDEX_SCHEMA_CONSTRAINT,null);
  }
  updateSchemaVertex(indexVertex);
  return new JanusGraphIndexWrapper(indexVertex.asIndexType());
}
