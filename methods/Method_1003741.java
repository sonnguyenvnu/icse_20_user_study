/** 
 * Deprecated. <p> Use  {@link #replace(Promise)}.
 * @param next the promise to replace {@code this} with
 * @param < O > the type of value provided by the replacement promise
 * @return a promise
 * @deprecated replaced by {@link #replace(Promise)} as of 1.1.0
 */
@Deprecated default <O>Promise<O> next(Promise<O> next){
  return flatMap(in -> next);
}
