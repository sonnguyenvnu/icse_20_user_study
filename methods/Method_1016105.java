/** 
 * Returns whether array contains the specified text or not.
 * @param number text to find
 * @param array  array to process
 * @return true if array contains the specified text, false otherwise
 */
public static boolean contains(final String number,final String[] array){
  return indexOf(number,array) != -1;
}
