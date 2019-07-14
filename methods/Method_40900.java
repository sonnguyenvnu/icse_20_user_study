/** 
 * Returns a predicate that returns whether any of the  {@code failures} are assignable from an execution failure.
 */
static <R>BiPredicate<R,Throwable> failurePredicateFor(List<Class<? extends Throwable>> failures){
  return (t,u) -> {
    if (u == null)     return false;
    for (    Class<? extends Throwable> failureType : failures)     if (failureType.isAssignableFrom(u.getClass()))     return true;
    return false;
  }
;
}
