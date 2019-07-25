/** 
 * Partially apply this function by taking its first two arguments.
 * @param a the first argument
 * @param b the second argument
 * @return an {@link Fn4} that takes the remaining arguments and returns the result
 */
@Override default Fn4<C,D,E,F,G> apply(A a,B b){
  return (c,d,e,f) -> apply(a,b,c,d,e,f);
}
