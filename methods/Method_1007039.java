/** 
 * If this is a left, then return the left value in right, or vice versa.
 * @return The value of this either swapped to the opposing side.
 */
public final Either<B,A> swap(){
  return either(right_(),left_());
}
