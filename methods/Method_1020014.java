/** 
 * Returns an  {@link Fn2} that takes the first two arguments as a <code>{@link Product2}&lt;A, B&gt;</code> and the third argument.
 * @return an {@link Fn2} taking a {@link Product2} and the third argument
 */
@Override default Fn2<? super Product2<? extends A,? extends B>,C,D> uncurry(){
  return (ab,c) -> apply(ab._1(),ab._2(),c);
}
