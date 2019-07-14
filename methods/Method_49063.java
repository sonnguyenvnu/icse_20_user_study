@Override public EdgeLabel getOrCreateEdgeLabel(String name){
  return getAutoStartTx().getOrCreateEdgeLabel(name);
}
