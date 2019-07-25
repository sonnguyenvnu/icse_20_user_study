@Override public Set<V> out(V vertex){
  return out.get(vertex).orElseThrow(() -> new IllegalArgumentException("no such vertex")).keys();
}
