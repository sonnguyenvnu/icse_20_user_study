/** 
 * Removes an element from a copy of the array and returns the copy. If the element is not present, then the original array is returned unmodified.
 * @param array The original array, or null to represent an empty array.
 * @param element The element to remove.
 * @return A new array that contains all of the elements of the original arrayexcept the first copy of the specified element removed.  If the specified element was not present, then returns the original array.  Returns null if the result would be an empty array.
 */
@SuppressWarnings("unchecked") public static <T>T[] removeElement(Class<T> kind,T[] array,T element){
  if (array != null) {
    final int length=array.length;
    for (int i=0; i < length; i++) {
      if (array[i] == element) {
        if (length == 1) {
          return null;
        }
        T[] result=(T[])Array.newInstance(kind,length - 1);
        System.arraycopy(array,0,result,0,i);
        System.arraycopy(array,i + 1,result,i,length - i - 1);
        return result;
      }
    }
  }
  return array;
}
