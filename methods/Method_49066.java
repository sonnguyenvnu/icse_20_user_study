@Override public VertexLabel getOrCreateVertexLabel(String name){
  return getAutoStartTx().getOrCreateVertexLabel(name);
}
