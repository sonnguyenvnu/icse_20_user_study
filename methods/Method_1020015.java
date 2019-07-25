/** 
 * Flip the order of the first two arguments.
 * @return an {@link Fn4}&lt;B, A, C, D, E&gt;
 */
@Override default Fn4<B,A,C,D,E> flip(){
  return (b,a,c,d) -> apply(a,b,c,d);
}
