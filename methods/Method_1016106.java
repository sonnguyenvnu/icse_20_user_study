/** 
 * Returns whether array contains the specified object or not.
 * @param object object to find
 * @param array  array to process
 * @return true if array contains the specified object, false otherwise
 */
public static boolean contains(final Object object,final Object[] array){
  return indexOf(object,array) != -1;
}
