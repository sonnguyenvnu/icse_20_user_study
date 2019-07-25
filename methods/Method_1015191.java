@Override public LinearSet<V> difference(ISet<V> s){
  if (s instanceof LinearSet) {
    return new LinearSet<V>(map.difference(((LinearSet<V>)s).map));
  }
 else {
    LinearMap<V,Void> m=map.clone();
    s.forEach(m::remove);
    return new LinearSet<V>(m);
  }
}
