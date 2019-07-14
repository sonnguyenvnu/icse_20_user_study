@Override public EdgeLabel addConnection(EdgeLabel edgeLabel,VertexLabel outVLabel,VertexLabel inVLabel){
  return getAutoStartTx().addConnection(edgeLabel,outVLabel,inVLabel);
}
