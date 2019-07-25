@Override public DirectedGraph<V,E> linear(){
  return isLinear() ? this : new DirectedGraph<>(true,out,in);
}
