/** 
 * Flip the order of the first two arguments.
 * @return an {@link Fn8} that takes the first and second arguments in reversed order
 */
@Override default Fn8<B,A,C,D,E,F,G,H,I> flip(){
  return (b,a,c,d,e,f,g,h) -> apply(a,b,c,d,e,f,g,h);
}
