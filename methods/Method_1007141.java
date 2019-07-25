/** 
 * Returns <code>true</code> if the predicate holds for at least one of the elements of this stream, <code>false</code> otherwise (<code>false</code> for the empty stream).
 * @param f The predicate function to test on the elements of this stream.
 * @return <code>true</code> if the predicate holds for at least one of the elements of thisstream.
 */
public final boolean exists(final F<A,Boolean> f){
  return dropWhile(not(f)).isNotEmpty();
}
