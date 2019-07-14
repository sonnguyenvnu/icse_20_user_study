@Override public void changeName(JanusGraphSchemaElement element,String newName){
  Preconditions.checkArgument(StringUtils.isNotBlank(newName),"Invalid name: %s",newName);
  JanusGraphSchemaVertex schemaVertex=getSchemaVertex(element);
  String oldName=schemaVertex.name();
  if (oldName.equals(newName))   return;
  JanusGraphSchemaCategory schemaCategory=schemaVertex.valueOrNull(BaseKey.SchemaCategory);
  Preconditions.checkArgument(schemaCategory.hasName(),"Invalid schema element: %s",element);
  if (schemaVertex instanceof RelationType) {
    InternalRelationType relType=(InternalRelationType)schemaVertex;
    if (relType.getBaseType() != null) {
      newName=composeRelationTypeIndexName(relType.getBaseType(),newName);
    }
 else     assert !(element instanceof RelationTypeIndex);
    JanusGraphSchemaCategory cat=relType.isEdgeLabel() ? JanusGraphSchemaCategory.EDGELABEL : JanusGraphSchemaCategory.PROPERTYKEY;
    SystemTypeManager.throwIfSystemName(cat,newName);
  }
 else   if (element instanceof VertexLabel) {
    SystemTypeManager.throwIfSystemName(JanusGraphSchemaCategory.VERTEXLABEL,newName);
  }
 else   if (element instanceof JanusGraphIndex) {
    checkIndexName(newName);
  }
  transaction.addProperty(schemaVertex,BaseKey.SchemaName,schemaCategory.getSchemaName(newName));
  updateConnectionEdgeConstraints(schemaVertex,oldName,newName);
  updateSchemaVertex(schemaVertex);
  schemaVertex.resetCache();
  updatedTypes.add(schemaVertex);
}
