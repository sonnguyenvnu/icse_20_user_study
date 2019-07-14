public JanusGraphSchemaElement getSchemaElement(long id){
  JanusGraphVertex v=transaction.getVertex(id);
  if (v == null)   return null;
  if (v instanceof RelationType) {
    if (((InternalRelationType)v).getBaseType() == null)     return (RelationType)v;
    return new RelationTypeIndexWrapper((InternalRelationType)v);
  }
  if (v instanceof JanusGraphSchemaVertex) {
    JanusGraphSchemaVertex sv=(JanusGraphSchemaVertex)v;
    if (sv.getDefinition().containsKey(TypeDefinitionCategory.INTERNAL_INDEX)) {
      return new JanusGraphIndexWrapper(sv.asIndexType());
    }
  }
  throw new IllegalArgumentException("Not a valid schema element vertex: " + id);
}
