/** 
 * Performs function application within an iterable (applicative functor pattern).
 * @param f The iterable function to apply.
 * @return A new iterable after applying the given iterable function to the wrapped iterable.
 */
public <B>IterableW<B> apply(final Iterable<F<A,B>> f){
  return wrap(f).bind(this::map);
}
