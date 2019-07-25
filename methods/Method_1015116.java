@Override public DirectedAcyclicGraph<V,E> transpose(){
  return new DirectedAcyclicGraph<>(graph.transpose(),bottom,top);
}
