@Override public Iterator<Vertex> vertices(Object... vertexIds){
  return getAutoStartTx().vertices(vertexIds);
}
