/** 
 * Returns an  {@link Fn4} that takes the first two arguments as a <code>{@link Product2}&lt;A, B&gt;</code> and the remaining arguments.
 * @return an {@link Fn4} taking a {@link Product2} and the remaining arguments
 */
@Override default Fn4<? super Product2<? extends A,? extends B>,C,D,E,F> uncurry(){
  return (ab,c,d,e) -> apply(ab._1(),ab._2(),c,d,e);
}
