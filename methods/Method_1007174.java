/** 
 * Flips the arguments of this function.
 * @return A new function with the arguments of this function flipped.
 */
public final F2W<B,A,C> flip(){
  return lift(F2Functions.flip(this));
}
