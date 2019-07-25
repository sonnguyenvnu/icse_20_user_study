/** 
 * Flip the order of the arguments.
 * @return an {@link Fn2}&lt;B, A, C&gt;
 */
default Fn2<B,A,C> flip(){
  return (b,a) -> apply(a,b);
}
