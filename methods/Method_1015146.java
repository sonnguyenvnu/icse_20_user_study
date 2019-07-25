public static <V,E>boolean equals(IGraph<V,E> a,IGraph<V,E> b){
  if (a.isDirected() != b.isDirected() || !a.vertices().equals(b.vertices())) {
    return false;
  }
  for (  V v : a.vertices()) {
    ISet<V> aOut=a.out(v);
    ISet<V> bOut=b.out(v);
    if (!aOut.equals(bOut)) {
      return false;
    }
    for (    V w : aOut) {
      if (!Objects.equals(a.edge(v,w),b.edge(v,w))) {
        return false;
      }
    }
  }
  return true;
}
