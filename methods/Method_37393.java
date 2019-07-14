/** 
 * Creates default implementation of the type cache.
 */
@SuppressWarnings("unchecked") public static <A>TypeCache<A> createDefault(){
  return (TypeCache<A>)Defaults.implementation.get();
}
