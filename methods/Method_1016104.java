/** 
 * Returns whether array contains the specified character or not.
 * @param number character to find
 * @param array  array to process
 * @return true if array contains the specified character, false otherwise
 */
public static boolean contains(final char number,final char[] array){
  return indexOf(number,array) != -1;
}
