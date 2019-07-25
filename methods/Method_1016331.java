@Override public Set<String> recommend(final String s){
  final Set<String> a=new HashSet<String>();
  if (s.isEmpty()) {
    return a;
  }
  final SortedMap<String,List<Integer>> tail=this.name2ids.tailMap(s);
  for (  final String name : tail.keySet()) {
    if (this.caseIncensitiveStartsWith(name,s)) {
      a.add(name);
    }
 else {
      break;
    }
  }
  return a;
}
