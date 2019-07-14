@Override public RelationTypeIndex getRelationIndex(RelationType type,String name){
  Preconditions.checkArgument(type != null);
  Preconditions.checkArgument(StringUtils.isNotBlank(name));
  String composedName=composeRelationTypeIndexName(type,name);
  JanusGraphVertex v=Iterables.getOnlyElement(QueryUtil.getVertices(transaction,BaseKey.SchemaName,JanusGraphSchemaCategory.getRelationTypeName(composedName)),null);
  if (v == null)   return null;
  assert v instanceof InternalRelationType;
  return new RelationTypeIndexWrapper((InternalRelationType)v);
}
