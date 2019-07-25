public EdgeId _apply(final Edge element){
  return new EdgeSeed(element.getSource(),element.getDestination(),element.isDirected());
}
