/** 
 * Promotes the given function to a concurrent function that returns a Promise.
 * @param f A given function to promote to a concurrent function.
 * @return A function that is applied concurrently when given an argument, yielding a Promise of the resultthat can be claimed in the future.
 */
public <A,B,C>F2<A,B,Promise<C>> promise(final F2<A,B,C> f){
  return P2.untuple(F1Functions.promiseK(F2Functions.tuple(f),strategy));
}
