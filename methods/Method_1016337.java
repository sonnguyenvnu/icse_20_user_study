/** 
 * check database tables against occurrences of this entity the anyname - String may be one of: - name of a town, villa, region etc - zip code - telephone prefix - kfz sign
 * @param anyname
 * @return
 */
@Override public TreeSet<GeoLocation> find(final String anyname,final boolean locationexact){
  final HashSet<Integer> r=new HashSet<Integer>();
  List<Integer> c;
  final StringBuilder an=new StringBuilder(anyname);
  if (locationexact) {
    c=this.name2ids.get(an);
    if (c != null) {
      r.addAll(c);
    }
  }
 else {
    final SortedMap<StringBuilder,List<Integer>> cities=this.name2ids.tailMap(an);
    for (    final Map.Entry<StringBuilder,List<Integer>> e : cities.entrySet()) {
      if (StringBuilderComparator.CASE_INSENSITIVE_ORDER.startsWith(e.getKey(),an)) {
        r.addAll(e.getValue());
      }
 else {
        break;
      }
    }
    c=this.kfz2ids.get(an);
    if (c != null) {
      r.addAll(c);
    }
    c=this.predial2ids.get(anyname);
    if (c != null) {
      r.addAll(c);
    }
    final Integer i=this.zip2id.get(anyname);
    if (i != null) {
      r.add(i);
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
