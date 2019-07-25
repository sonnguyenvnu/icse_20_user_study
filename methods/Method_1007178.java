/** 
 * Returns true if all the elements of the given list are true.
 * @param l A list to check for all the elements being true.
 * @return true if all the elements of the given list are true. False otherwise.
 */
public static boolean and(final List<Boolean> l){
  return Monoid.conjunctionMonoid.sumLeft(l);
}
