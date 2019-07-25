/** 
 * First-class predecessor function.
 * @return A function that returns the predecessor of a given natural number, or None if it's zero.
 */
public static F<Natural,Option<Natural>> pred_(){
  return Natural::pred;
}
