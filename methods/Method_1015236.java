/** 
 * @param s a set
 * @return an equivalent set, with the same equality semantics
 */
public static <V>Set<V> from(ISet<V> s){
  if (s instanceof Set) {
    return ((Set<V>)s).forked();
  }
 else {
    Set<V> result=new Set<V>(s.valueHash(),s.valueEquality()).linear();
    s.forEach(result::add);
    return result.forked();
  }
}
