@Override public TreeSet<GeoLocation> find(final String anyname,final boolean locationexact){
  final Set<Integer> r=new HashSet<Integer>();
  List<Integer> c;
  if (locationexact) {
    c=this.name2ids.get(anyname);
    if (c != null) {
      r.addAll(c);
    }
  }
 else {
    final SortedMap<String,List<Integer>> cities=this.name2ids.tailMap(anyname);
    for (    final Map.Entry<String,List<Integer>> e : cities.entrySet()) {
      if (this.caseIncensitiveStartsWith(e.getKey(),anyname)) {
        r.addAll(e.getValue());
      }
 else {
        break;
      }
    }
  }
  final TreeSet<GeoLocation> a=new TreeSet<GeoLocation>();
  for (  final Integer e : r) {
    final GeoLocation w=this.id2loc.get(e);
    if (w != null) {
      a.add(w);
    }
  }
  return a;
}
