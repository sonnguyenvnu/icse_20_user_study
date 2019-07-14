private void updateConnectionEdgeConstraints(JanusGraphSchemaVertex edgeLabel,String oldName,String newName){
  if (!(edgeLabel instanceof EdgeLabel))   return;
  ((EdgeLabel)edgeLabel).mappedConnections().stream().peek(s -> schemaCache.expireSchemaElement(s.getOutgoingVertexLabel().longId())).map(Connection::getConnectionEdge).forEach(edge -> {
    TypeDefinitionDescription desc=new TypeDefinitionDescription(TypeDefinitionCategory.CONNECTION_EDGE,newName);
    edge.property(BaseKey.SchemaDefinitionDesc.name(),desc);
  }
);
}
