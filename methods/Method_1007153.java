/** 
 * The expression <code>t1.union(t2)</code> takes the left-biased union of <code>t1</code> and <code>t2</code>. It prefers <code>t1</code> when duplicate keys are encountered.
 * @param t2 The other tree we wish to combine with this one
 * @return The combined TreeMap
 */
public TreeMap<K,V> union(TreeMap<K,V> t2){
  TreeMap<K,V> result=t2;
  for (  P2<K,V> p : this) {
    result=result.set(p._1(),p._2());
  }
  return result;
}
