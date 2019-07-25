/** 
 * A first-class version of the normalise function.
 * @return A function that normalises the given Callable by calling it and wrapping the result in a new Callable.
 */
public static <A>F<Callable<A>,Callable<A>> normalise(){
  return Callables::normalise;
}
