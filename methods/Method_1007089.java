/** 
 * Returns a tuple where the first element is the longest prefix of this list that satisfies the given predicate and the second element is the remainder of the list.
 * @param p A predicate to be satisfied by a prefix of this list.
 * @return A tuple where the first element is the longest prefix of this list that satisfiesthe given predicate and the second element is the remainder of the list.
 */
public final P2<List<A>,List<A>> span(final F<A,Boolean> p){
  final Buffer<A> b=empty();
  for (List<A> xs=this; xs.isNotEmpty(); xs=xs.tail()) {
    if (p.f(xs.head()))     b.snoc(xs.head());
 else     return p(b.toList(),xs);
  }
  return p(b.toList(),List.nil());
}
