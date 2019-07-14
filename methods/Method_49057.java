@Override public EdgeLabel addProperties(EdgeLabel edgeLabel,PropertyKey... keys){
  return getAutoStartTx().addProperties(edgeLabel,keys);
}
