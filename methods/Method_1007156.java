/** 
 * Returns <code>true</code> if this is a failure or returns the result of the application of the given function to the success value.
 * @param f The predicate function to test on this success value.
 * @return <code>true</code> if this is a failure or returns the result of the application of the givenfunction to the success value.
 */
public final boolean forall(final F<T,Boolean> f){
  return e.right().forall(f);
}
