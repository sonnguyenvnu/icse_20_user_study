/** 
 * Reverse this list in constant stack space.
 * @return A new list that is the reverse of this one.
 */
public final List<A> reverse(){
  return foldLeft((as,a) -> cons(a,as),nil());
}
