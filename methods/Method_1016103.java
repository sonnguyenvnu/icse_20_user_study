/** 
 * Returns whether array contains the specified number or not.
 * @param number number to find
 * @param array  array to process
 * @return true if array contains the specified number, false otherwise
 */
public static boolean contains(final double number,final double[] array){
  return indexOf(number,array) != -1;
}
