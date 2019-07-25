/** 
 * Drops the given number of elements from the head of this sequence if they are available.
 * @param n The number of elements to drop from this sequence.
 * @return A sequence consisting of all elements of this sequence except the first n ones, or else the empty sequence,if this sequence has less than n elements.
 */
public Seq<A> drop(final int n){
  return split(n)._2();
}
