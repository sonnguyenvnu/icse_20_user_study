/** 
 * @param set another set
 * @return a {@code LinearSet} containing the same elements, with the same equality semantics
 */
public static <V>LinearSet<V> from(ISet<V> set){
  if (set instanceof LinearSet) {
    return ((LinearSet<V>)set).clone();
  }
 else {
    LinearSet<V> result=new LinearSet<V>((int)set.size(),set.valueHash(),set.valueEquality());
    set.forEach(result::add);
    return result;
  }
}
