@Override public DirectedGraph<V,E> link(V from,V to){
  return link(from,to,null,(BinaryOperator<E>)MERGE_LAST_WRITE_WINS);
}
