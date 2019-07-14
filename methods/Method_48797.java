@Override public GraphComputer vertices(final Traversal<Vertex,Vertex> vertexFilter){
  this.graphFilter.setVertexFilter(vertexFilter);
  return this;
}
