/** 
 * Returns all but the last element of this list. Undefiend for the empty list.
 * @return All but the last element of this list. Undefiend for the empty list.
 */
public final List<A> init(){
  List<A> ys=this;
  final Buffer<A> a=empty();
  while (ys.isNotEmpty() && ys.tail().isNotEmpty()) {
    a.snoc(ys.head());
    ys=ys.tail();
  }
  return a.toList();
}
