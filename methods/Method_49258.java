@Override public EdgeLabel makeEdgeLabel(EdgeLabelMaker factory){
  throw new IllegalArgumentException("Edge Label with given name does not exist: " + factory.getName());
}
