/** 
 * read the dictionary and construct a set of recommendations to a given string
 * @param s input value that is used to match recommendations
 * @return a set that contains all words that start with the input value
 */
@Override public Set<String> recommend(final String s){
  final Set<String> a=new HashSet<String>();
  final StringBuilder an=new StringBuilder(s);
  if (s.isEmpty()) {
    return a;
  }
  final SortedMap<StringBuilder,List<Integer>> t=this.name2ids.tailMap(an);
  for (  final StringBuilder r : t.keySet()) {
    if (StringBuilderComparator.CASE_INSENSITIVE_ORDER.startsWith(r,an)) {
      a.add(r.toString());
    }
 else {
      break;
    }
  }
  return a;
}
