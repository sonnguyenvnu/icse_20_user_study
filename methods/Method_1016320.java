/** 
 * read the dictionary and construct a set of recommendations to a given string
 * @param s input value that is used to match recommendations
 * @return set that contains all words that start or end with the input value
 */
public Set<StringBuilder> recommend(StringBuilder string){
  Set<StringBuilder> ret=new HashSet<StringBuilder>();
  for (  Dictionary dict : this.dictionaries.values()) {
    ret.addAll(dict.recommend(string));
  }
  final SortedMap<StringBuilder,AtomicInteger> u=commonWords.tailMap(string);
  StringBuilder vv;
  try {
    for (    final Map.Entry<StringBuilder,AtomicInteger> v : u.entrySet()) {
      vv=v.getKey();
      if (StringBuilderComparator.CASE_INSENSITIVE_ORDER.startsWith(vv,string) && vv.length() > string.length()) {
        ret.add(vv);
      }
 else {
        break;
      }
    }
  }
 catch (  final ConcurrentModificationException e) {
    Data.logger.warn("",e);
  }
  return ret;
}
