/** 
 * Flip the order of the first two arguments.
 * @return an {@link Fn7} that takes the first and second arguments in reversed order
 */
@Override default Fn7<B,A,C,D,E,F,G,H> flip(){
  return (b,a,c,d,e,f,g) -> apply(a,b,c,d,e,f,g);
}
