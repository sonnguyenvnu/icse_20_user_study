private List<Set<K>> toResult(List<Cluster<K>> clusters_){
  List<Set<K>> result=new ArrayList<Set<K>>(clusters_.size());
  for (  Cluster<K> c : clusters_) {
    Set<K> s=new HashSet<K>();
    for (    Document<K> d : c.documents_) {
      s.add(d.id_);
    }
    result.add(s);
  }
  return result;
}
