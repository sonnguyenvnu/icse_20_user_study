/** 
 * Curries this wrapped function to a wrapped function of arity-1 that returns another wrapped function.
 * @return a wrapped function of arity-1 that returns another wrapped function.
 */
public final F1W<A,F<B,C>> curry(){
  return F1W.lift(F2Functions.curry(this));
}
