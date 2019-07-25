/** 
 * Performs a side-effect for the value of this optional value.
 * @param f The side-effect to perform for the given element.
 * @return The unit value.
 */
public final Unit foreach(final F<A,Unit> f){
  return isSome() ? f.f(some()) : unit();
}
