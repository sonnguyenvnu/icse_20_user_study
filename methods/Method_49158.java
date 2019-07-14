private void checkPropertyConstraintForVertexOrCreatePropertyConstraint(JanusGraphVertex vertex,PropertyKey key){
  if (config.hasDisabledSchemaConstraints())   return;
  VertexLabel vertexLabel=vertex.vertexLabel();
  if (vertexLabel instanceof BaseVertexLabel)   return;
  Collection<PropertyKey> propertyKeys=vertexLabel.mappedProperties();
  if (propertyKeys.contains(key))   return;
  config.getAutoSchemaMaker().makePropertyConstraintForVertex(vertexLabel,key,this);
}
