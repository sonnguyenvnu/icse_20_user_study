private void checkConnectionConstraintOrCreateConnectionConstraint(JanusGraphVertex outVertex,JanusGraphVertex inVertex,EdgeLabel edgeLabel){
  if (config.hasDisabledSchemaConstraints())   return;
  VertexLabel outVertexLabel=outVertex.vertexLabel();
  if (outVertexLabel instanceof BaseVertexLabel)   return;
  VertexLabel inVertexLabel=inVertex.vertexLabel();
  if (inVertexLabel instanceof BaseVertexLabel)   return;
  Collection<Connection> connections=outVertexLabel.mappedConnections();
  for (  Connection connection : connections) {
    if (connection.getIncomingVertexLabel() != inVertexLabel)     continue;
    if (connection.getEdgeLabel().equals(edgeLabel.name()))     return;
  }
  config.getAutoSchemaMaker().makeConnectionConstraint(edgeLabel,outVertexLabel,inVertexLabel,this);
}
