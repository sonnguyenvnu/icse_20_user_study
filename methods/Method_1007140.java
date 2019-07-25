/** 
 * Returns the element at the given index if it exists, fails otherwise.
 * @param i The index at which to get the element to return.
 * @return The element at the given index if it exists, fails otherwise.
 */
public final A index(final int i){
  if (i < 0)   throw error("index " + i + " out of range on stream");
 else {
    Stream<A> xs=this;
    for (int c=0; c < i; c++) {
      if (xs.isEmpty())       throw error("index " + i + " out of range on stream");
      xs=xs.tail()._1();
    }
    if (xs.isEmpty())     throw error("index " + i + " out of range on stream");
    return xs.head();
  }
}
