@Override public DirectedAcyclicGraph<V,E> forked(){
  return graph.isLinear() ? new DirectedAcyclicGraph<>(graph.forked(),top.forked(),bottom.forked()) : this;
}
