@Override public VertexLabel addProperties(VertexLabel vertexLabel,PropertyKey... keys){
  for (  PropertyKey key : keys) {
    addSchemaEdge(vertexLabel,key,TypeDefinitionCategory.PROPERTY_KEY_EDGE,null);
  }
  return vertexLabel;
}
