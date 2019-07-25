/** 
 * Returns an  {@link Fn1} that takes the arguments as a <code>{@link Product2}&lt;A, B&gt;</code>.
 * @return an {@link Fn1} taking a {@link Product2}
 */
default Fn1<? super Product2<? extends A,? extends B>,C> uncurry(){
  return (ab) -> apply(ab._1(),ab._2());
}
