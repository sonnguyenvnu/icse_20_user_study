private JanusGraphIndex createCompositeIndex(String indexName,ElementCategory elementCategory,boolean unique,JanusGraphSchemaType constraint,PropertyKey... keys){
  checkIndexName(indexName);
  Preconditions.checkArgument(keys != null && keys.length > 0,"Need to provide keys to index [%s]",indexName);
  Preconditions.checkArgument(!unique || elementCategory == ElementCategory.VERTEX,"Unique indexes can only be created on vertices [%s]",indexName);
  boolean allSingleKeys=true;
  boolean oneNewKey=false;
  for (  PropertyKey key : keys) {
    Preconditions.checkArgument(key != null && key instanceof PropertyKeyVertex,"Need to provide valid keys: %s",key);
    if (key.cardinality() != Cardinality.SINGLE)     allSingleKeys=false;
    if (key.isNew())     oneNewKey=true;
 else     updatedTypes.add((PropertyKeyVertex)key);
  }
  Cardinality indexCardinality;
  if (unique)   indexCardinality=Cardinality.SINGLE;
 else   indexCardinality=(allSingleKeys ? Cardinality.SET : Cardinality.LIST);
  boolean canIndexBeEnabled=oneNewKey || (constraint != null && constraint.isNew());
  TypeDefinitionMap def=new TypeDefinitionMap();
  def.setValue(TypeDefinitionCategory.INTERNAL_INDEX,true);
  def.setValue(TypeDefinitionCategory.ELEMENT_CATEGORY,elementCategory);
  def.setValue(TypeDefinitionCategory.BACKING_INDEX,Token.INTERNAL_INDEX_NAME);
  def.setValue(TypeDefinitionCategory.INDEXSTORE_NAME,indexName);
  def.setValue(TypeDefinitionCategory.INDEX_CARDINALITY,indexCardinality);
  def.setValue(TypeDefinitionCategory.STATUS,canIndexBeEnabled ? SchemaStatus.ENABLED : SchemaStatus.INSTALLED);
  JanusGraphSchemaVertex indexVertex=transaction.makeSchemaVertex(JanusGraphSchemaCategory.GRAPHINDEX,indexName,def);
  for (int i=0; i < keys.length; i++) {
    Parameter[] paras={ParameterType.INDEX_POSITION.getParameter(i)};
    addSchemaEdge(indexVertex,keys[i],TypeDefinitionCategory.INDEX_FIELD,paras);
  }
  Preconditions.checkArgument(constraint == null || (elementCategory.isValidConstraint(constraint) && constraint instanceof JanusGraphSchemaVertex));
  if (constraint != null) {
    addSchemaEdge(indexVertex,(JanusGraphSchemaVertex)constraint,TypeDefinitionCategory.INDEX_SCHEMA_CONSTRAINT,null);
  }
  updateSchemaVertex(indexVertex);
  JanusGraphIndexWrapper index=new JanusGraphIndexWrapper(indexVertex.asIndexType());
  if (!oneNewKey)   updateIndex(index,SchemaAction.REGISTER_INDEX);
  return index;
}
