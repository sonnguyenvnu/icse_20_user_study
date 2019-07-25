@Override public DirectedGraph<V,E> forked(){
  return isLinear() ? new DirectedGraph<>(false,out,in) : this;
}
