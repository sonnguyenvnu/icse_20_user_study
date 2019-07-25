/** 
 * Turn a list of functions into a function returning a list.
 * @param fs The list of functions to sequence into a single function that returns a list.
 * @return A function that, when given an argument, applies all the functions in the given list to itand returns a list of the results.
 */
public static <A,B>F<B,List<A>> sequence_(final List<F<B,A>> fs){
  return fs.foldRight(Function.lift(List.cons()),Function.constant(List.nil()));
}
