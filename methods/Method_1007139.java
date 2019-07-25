/** 
 * Reverse this stream in constant stack space.
 * @return A new stream that is the reverse of this one.
 */
public final Stream<A> reverse(){
  return foldLeft((as,a) -> cons(a,() -> as),Stream.nil());
}
