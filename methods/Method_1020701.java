/** 
 * Applies a function to this Solo and returns the Solo it returned.
 * @param < R > the result type
 * @param composer the function that receives this Solo and should return a Solo
 * @return the new Solo instance
 */
public final <R>Solo<R> compose(Function<? super Solo<T>,? extends Solo<R>> composer){
  return to(composer);
}
