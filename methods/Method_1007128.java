/** 
 * Takes the given number of elements from the head of this sequence if they are available.
 * @param n The maximum number of elements to take from this sequence.
 * @return A sequence consisting only of the first n elements of this sequence, or else the whole sequence,if it has less than n elements.
 */
public Seq<A> take(final int n){
  return split(n)._1();
}
