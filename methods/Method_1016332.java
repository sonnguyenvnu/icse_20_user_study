@Override public Set<StringBuilder> recommend(final StringBuilder s){
  final Set<StringBuilder> a=new HashSet<StringBuilder>();
  if (s.length() == 0) {
    return a;
  }
  final String sString=s.toString();
  final SortedMap<String,List<Integer>> tail=this.name2ids.tailMap(sString);
  for (  final String name : tail.keySet()) {
    if (this.caseIncensitiveStartsWith(name,sString)) {
      a.add(new StringBuilder(name));
    }
 else {
      break;
    }
  }
  return a;
}
