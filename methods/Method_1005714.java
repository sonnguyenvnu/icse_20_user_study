/** 
 * <p>Copies the given array and adds the given element at the end of the new array.</p> <p>The new array contains the same elements of the input array plus the given element in the last position. The component type of the new array is the same as that of the input array.</p> <p>If the input array is  {@code null}, a new one element array is returned whose component type is the same as the element.</p> <pre> ArrayUtils.add(null, true)          = [true] ArrayUtils.add([true], false)       = [true, false] ArrayUtils.add([true, false], true) = [true, false, true] </pre>
 * @param array  the array to copy and add the element to, may be {@code null}
 * @param element  the object to add at the last index of the new array
 * @return A new array containing the existing elements plus the new element
 * @since 2.1
 */
public static boolean[] add(boolean[] array,boolean element){
  boolean[] newArray=(boolean[])copyArrayGrow1(array,Boolean.TYPE);
  newArray[newArray.length - 1]=element;
  return newArray;
}
