private void setStatusVertex(JanusGraphSchemaVertex vertex,SchemaStatus status){
  Preconditions.checkArgument(vertex instanceof RelationTypeVertex || vertex.asIndexType().isCompositeIndex());
  for (  JanusGraphVertexProperty p : vertex.query().types(BaseKey.SchemaDefinitionProperty).properties()) {
    if (p.<TypeDefinitionDescription>valueOrNull(BaseKey.SchemaDefinitionDesc).getCategory() == TypeDefinitionCategory.STATUS) {
      if (p.value().equals(status))       return;
 else       p.remove();
    }
  }
  JanusGraphVertexProperty p=transaction.addProperty(vertex,BaseKey.SchemaDefinitionProperty,status);
  p.property(BaseKey.SchemaDefinitionDesc.name(),TypeDefinitionDescription.of(TypeDefinitionCategory.STATUS));
}
