/** 
 * Creates a new HashSet using the given Equal and Hash
 */
public static <A>HashSet<A> empty(final Equal<A> e,final Hash<A> h){
  return new HashSet<>(e,h);
}
