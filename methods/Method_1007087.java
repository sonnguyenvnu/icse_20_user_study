/** 
 * Filters elements from this list by returning only elements which produce <code>true</code> when the given function is applied to them.
 * @param f The predicate function to filter on.
 * @return A new list whose elements all match the given predicate.
 */
public final List<A> filter(final F<A,Boolean> f){
  final Buffer<A> b=empty();
  for (List<A> xs=this; xs.isNotEmpty(); xs=xs.tail()) {
    final A h=xs.head();
    if (f.f(h)) {
      b.snoc(h);
    }
  }
  return b.toList();
}
