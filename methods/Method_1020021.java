/** 
 * Partially apply this function by taking its first three arguments.
 * @param a the first argument
 * @param b the second argument
 * @param c the third argument
 * @return an {@link Fn3} that takes remaining arguments and returns the result
 */
@Override default Fn3<D,E,F,G> apply(A a,B b,C c){
  return (d,e,f) -> apply(a,b,c,d,e,f);
}
