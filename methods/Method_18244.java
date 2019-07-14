/** 
 * Given the current size of an array, returns an ideal size to which the array should grow. This is the double of  {@param currentSize} but but should not be relied upon to do so in the future.
 * @param currentSize The current size of the array.
 */
private static int growSize(int currentSize){
  return currentSize <= 2 ? 4 : currentSize * 2;
}
