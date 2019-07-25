default IGraph<V,E> add(IEdge<V,E> edge){
  return link(edge.from(),edge.to(),edge.value());
}
