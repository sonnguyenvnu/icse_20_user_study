/** 
 * Function application in an environment (Applicative Functor).
 * @param g A function with the same argument type as this function, yielding a function that takes the returnvalue of this function.
 * @return A new function that invokes the given function on its argument, yielding a new function that is thenapplied to the result of applying this function to the argument.
 */
public final <C>F1W<A,C> apply(final F<A,F<B,C>> g){
  return lift(F1Functions.apply(this,g));
}
