@Override public E edge(V from,V to){
  Object e=((Map)edges).get(new VertexSet<>(from,to),DEFAULT);
  if (e == DEFAULT) {
    throw new IllegalArgumentException("no such edge");
  }
 else {
    return (E)e;
  }
}
