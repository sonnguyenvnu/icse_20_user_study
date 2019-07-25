@Override public Set<V> out(V vertex){
  return adjacent.get(vertex).orElseThrow(() -> new IllegalArgumentException("no such vertex " + vertex));
}
