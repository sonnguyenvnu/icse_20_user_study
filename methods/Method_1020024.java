/** 
 * Returns an  {@link Fn6} that takes the first two arguments as a <code>{@link Product2}&lt;A, B&gt;</code> and the remaining arguments.
 * @return an {@link Fn6} taking a {@link Product2} and the remaining arguments
 */
@Override default Fn6<? super Product2<? extends A,? extends B>,C,D,E,F,G,H> uncurry(){
  return (ab,c,d,e,f,g) -> apply(ab._1(),ab._2(),c,d,e,f,g);
}
