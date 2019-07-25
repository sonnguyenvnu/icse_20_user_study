/** 
 * First-class successor function.
 * @return A function that returns the successor of a given natural number.
 */
public static F<Natural,Natural> succ_(){
  return Natural::succ;
}
