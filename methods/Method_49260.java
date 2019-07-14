@Override public VertexLabel makeVertexLabel(VertexLabelMaker factory){
  throw new IllegalArgumentException("Vertex Label with given name does not exist: " + factory.getName());
}
