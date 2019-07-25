/** 
 * Provides a transformation from a function to a Iterable-valued function that is equivalent to it. The first-class Kleisli arrow for Iterables.
 * @return A transformation from a function to the equivalent Iterable-valued function.
 */
public static <A,B>F<F<A,B>,F<A,IterableW<B>>> arrow(){
  return IterableW::iterable;
}
