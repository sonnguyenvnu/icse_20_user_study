@Override public DirectedGraph<V,E> link(V from,V to,E edge){
  return link(from,to,edge,(BinaryOperator<E>)MERGE_LAST_WRITE_WINS);
}
