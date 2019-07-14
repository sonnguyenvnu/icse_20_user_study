public final JanusGraphSchemaVertex makeSchemaVertex(JanusGraphSchemaCategory schemaCategory,String name,TypeDefinitionMap definition){
  verifyOpen();
  Preconditions.checkArgument(!schemaCategory.hasName() || StringUtils.isNotBlank(name),"Need to provide a valid name for type [%s]",schemaCategory);
  schemaCategory.verifyValidDefinition(definition);
  JanusGraphSchemaVertex schemaVertex;
  if (schemaCategory.isRelationType()) {
    if (schemaCategory == JanusGraphSchemaCategory.PROPERTYKEY) {
      schemaVertex=new PropertyKeyVertex(this,IDManager.getTemporaryVertexID(IDManager.VertexIDType.UserPropertyKey,temporaryIds.nextID()),ElementLifeCycle.New);
    }
 else {
      assert schemaCategory == JanusGraphSchemaCategory.EDGELABEL;
      schemaVertex=new EdgeLabelVertex(this,IDManager.getTemporaryVertexID(IDManager.VertexIDType.UserEdgeLabel,temporaryIds.nextID()),ElementLifeCycle.New);
    }
  }
 else   if (schemaCategory == JanusGraphSchemaCategory.VERTEXLABEL) {
    schemaVertex=new VertexLabelVertex(this,IDManager.getTemporaryVertexID(IDManager.VertexIDType.GenericSchemaType,temporaryIds.nextID()),ElementLifeCycle.New);
  }
 else {
    schemaVertex=new JanusGraphSchemaVertex(this,IDManager.getTemporaryVertexID(IDManager.VertexIDType.GenericSchemaType,temporaryIds.nextID()),ElementLifeCycle.New);
  }
  graph.assignID(schemaVertex,BaseVertexLabel.DEFAULT_VERTEXLABEL);
  Preconditions.checkArgument(schemaVertex.longId() > 0);
  if (schemaCategory.hasName())   addProperty(schemaVertex,BaseKey.SchemaName,schemaCategory.getSchemaName(name));
  addProperty(schemaVertex,BaseKey.VertexExists,Boolean.TRUE);
  addProperty(schemaVertex,BaseKey.SchemaCategory,schemaCategory);
  updateSchemaVertex(schemaVertex);
  addProperty(schemaVertex,BaseKey.SchemaUpdateTime,times.getTime(times.getTime()));
  for (  Map.Entry<TypeDefinitionCategory,Object> def : definition.entrySet()) {
    JanusGraphVertexProperty p=addProperty(schemaVertex,BaseKey.SchemaDefinitionProperty,def.getValue());
    p.property(BaseKey.SchemaDefinitionDesc.name(),TypeDefinitionDescription.of(def.getKey()));
  }
  vertexCache.add(schemaVertex,schemaVertex.longId());
  if (schemaCategory.hasName())   newTypeCache.put(schemaCategory.getSchemaName(name),schemaVertex.longId());
  return schemaVertex;
}
