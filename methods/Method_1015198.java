/** 
 * @return a concatenation of the two lists, which is linear if {@code a} is linear
 */
public static <V>IList<V> concat(IList<V> a,IList<V> b){
  IList<V> result;
  if (a.size() == 0) {
    result=b.forked();
  }
 else   if (b.size() == 0) {
    result=a.forked();
  }
 else   if (a instanceof Concat && b instanceof Concat) {
    result=((Concat<V>)a).concat((Concat<V>)b);
  }
 else   if (a instanceof Concat) {
    result=((Concat<V>)a).concat(new Concat<>(b));
  }
 else   if (b instanceof Concat) {
    result=new Concat<>(a).concat((Concat<V>)b);
  }
 else {
    result=new Concat<V>(a,b);
  }
  return a.isLinear() ? result.linear() : result;
}
