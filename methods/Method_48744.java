public final boolean isSchemaVertexId(long id){
  return isRelationTypeId(id) || isVertexLabelVertexId(id) || isGenericSchemaVertexId(id);
}
