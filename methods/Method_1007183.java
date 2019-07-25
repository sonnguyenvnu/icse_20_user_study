/** 
 * Simultaneously covaries and contravaries a function.
 * @return A function that varies and covaries a function.
 */
public static <C,A extends C,B,D extends B>F<F<C,D>,F<A,B>> vary(){
  return Function::<A,B>vary;
}
