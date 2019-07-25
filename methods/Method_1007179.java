/** 
 * Returns true if all the elements of the given stream are true.
 * @param l A stream to check for all the elements being true.
 * @return true if all the elements of the given stream are true. False otherwise.
 */
public static boolean and(final Stream<Boolean> l){
  return Monoid.conjunctionMonoid.sumLeft(l);
}
