/** 
 * Returns a String representation of the specified measure specification.
 * @param sizeSpec the size specification to convert to a String
 * @return a String with the following format: "MeasureSpec: MODE SIZE"
 */
public static String toString(int sizeSpec){
  return View.MeasureSpec.toString(sizeSpec);
}
