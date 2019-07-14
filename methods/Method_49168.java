@Override public EdgeLabel addConnection(EdgeLabel edgeLabel,VertexLabel outVLabel,VertexLabel inVLabel){
  addSchemaEdge(outVLabel,inVLabel,TypeDefinitionCategory.CONNECTION_EDGE,edgeLabel.name());
  addSchemaEdge(edgeLabel,outVLabel,TypeDefinitionCategory.UPDATE_CONNECTION_EDGE,null);
  return edgeLabel;
}
