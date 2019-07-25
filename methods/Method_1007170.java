/** 
 * First-class function application in an environment.
 * @return A function that applies a given function within the environment of this function.
 */
public final <C>F1W<F<A,F<B,C>>,F<A,C>> apply(){
  return lift(F1Functions.apply(this));
}
