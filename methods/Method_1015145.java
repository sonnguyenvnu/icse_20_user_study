@Override public Graph<V,E> forked(){
  return isLinear() ? new Graph<>(false,adjacent,edges) : this;
}
