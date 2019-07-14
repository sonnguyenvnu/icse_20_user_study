@Override public JanusGraphMultiVertexQuery multiQuery(JanusGraphVertex... vertices){
  return getAutoStartTx().multiQuery(vertices);
}
