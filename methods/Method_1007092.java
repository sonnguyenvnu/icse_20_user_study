/** 
 * Sorts this list using the given order over elements using a <em>merge sort</em> algorithm.
 * @param o The order over the elements of this list.
 * @return A sorted list according to the given order.
 */
public final List<A> sort(final Ord<A> o){
  if (isEmpty())   return nil();
 else   if (tail().isEmpty())   return this;
 else {
final class Merge {
      List<A> merge(      List<A> xs,      List<A> ys,      final Ord<A> o){
        final Buffer<A> buf=empty();
        while (true) {
          if (xs.isEmpty()) {
            buf.append(ys);
            break;
          }
          if (ys.isEmpty()) {
            buf.append(xs);
            break;
          }
          final A x=xs.head();
          final A y=ys.head();
          if (o.isLessThanOrEqualTo(x,y)) {
            buf.snoc(x);
            xs=xs.tail();
          }
 else {
            buf.snoc(y);
            ys=ys.tail();
          }
        }
        return buf.toList();
      }
    }
    final P2<List<A>,List<A>> s=splitAt(length() / 2);
    return new Merge().merge(s._1().sort(o),s._2().sort(o),o);
  }
}
