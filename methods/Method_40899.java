/** 
 * Returns a predicate that evaluates the  {@code failurePredicate} against a failure.
 */
@SuppressWarnings("unchecked") static <R>BiPredicate<R,Throwable> failurePredicateFor(Predicate<? extends Throwable> failurePredicate){
  return (t,u) -> u != null && ((Predicate<Throwable>)failurePredicate).test(u);
}
