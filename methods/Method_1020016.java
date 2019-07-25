/** 
 * Returns an  {@link Fn3} that takes the first two arguments as a <code>{@link Product2}&lt;A, B&gt;</code> and the third and fourth arguments.
 * @return an {@link Fn3} taking a {@link Product2} and the third and fourth arguments
 */
@Override default Fn3<? super Product2<? extends A,? extends B>,C,D,E> uncurry(){
  return (ab,c,d) -> apply(ab._1(),ab._2(),c,d);
}
