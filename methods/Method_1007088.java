/** 
 * Removes the first element that equals the given object. To remove all matches, use <code>removeAll(e.eq(a))</code>
 * @param a The element to remove
 * @param e An <code>Equals</code> instance for the element's type.
 * @return A new list whose elements do not match the given predicate.
 */
public final List<A> delete(final A a,final Equal<A> e){
  final P2<List<A>,List<A>> p=span(compose(not,e.eq(a)));
  return p._2().isEmpty() ? p._1() : p._1().append(p._2().tail());
}
