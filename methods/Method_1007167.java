/** 
 * First-class function application in an environment.
 * @return A function that applies a given function within the environment of this function.
 */
public static <A,B,C>F<F<A,F<B,C>>,F<A,C>> apply(final F<A,B> f){
  return g -> apply(f,g);
}
