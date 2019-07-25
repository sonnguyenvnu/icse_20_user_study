@Override public LinearSet<V> union(ISet<V> s){
  if (s instanceof LinearSet) {
    return new LinearSet<V>(map.union(((LinearSet<V>)s).map));
  }
 else {
    LinearMap<V,Void> m=map.clone();
    s.forEach(e -> map.put(e,null));
    return new LinearSet<V>(m);
  }
}
