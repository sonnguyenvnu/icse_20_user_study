@Override public Set<StringBuilder> recommend(final StringBuilder s){
  final Set<StringBuilder> a=new HashSet<StringBuilder>();
  if (s.length() == 0) {
    return a;
  }
  final SortedMap<StringBuilder,List<Integer>> t=this.name2ids.tailMap(s);
  for (  final StringBuilder r : t.keySet()) {
    if (StringBuilderComparator.CASE_INSENSITIVE_ORDER.startsWith(r,s)) {
      a.add(r);
    }
 else {
      break;
    }
  }
  return a;
}
