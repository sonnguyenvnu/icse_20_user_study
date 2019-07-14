@Override public GraphComputer edges(final Traversal<Vertex,Edge> edgeFilter){
  this.graphFilter.setEdgeFilter(edgeFilter);
  return this;
}
