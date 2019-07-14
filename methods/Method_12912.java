/** 
 * Check whether the given array contains the given element.
 * @param array the array to check (may be {@code null}, in which case the return value will always be  {@code false})
 * @param element the element to check for
 * @return whether the element has been found in the given array
 */
public static boolean containsElement(Object[] array,Object element){
  if (array == null) {
    return false;
  }
  for (  Object arrayEle : array) {
    if (nullSafeEquals(arrayEle,element)) {
      return true;
    }
  }
  return false;
}
