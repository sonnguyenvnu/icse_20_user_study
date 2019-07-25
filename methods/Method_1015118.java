@Override public E edge(V from,V to){
  Map m=out.get(from).orElseThrow(() -> new IllegalArgumentException("no such edge"));
  Object e=m.get(to,DEFAULT);
  if (e == DEFAULT) {
    throw new IllegalArgumentException("no such edge");
  }
 else {
    return (E)e;
  }
}
