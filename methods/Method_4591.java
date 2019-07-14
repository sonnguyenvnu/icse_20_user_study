/** 
 * Returns an array that can store (at least)  {@code length} elements, which will be either a newarray or  {@code array} if it's not null and large enough.
 */
private static int[] ensureArrayCapacity(int[] array,int length){
  if (array == null) {
    return new int[length];
  }
 else   if (array.length >= length) {
    return array;
  }
 else {
    return new int[Math.max(array.length * 2,length)];
  }
}
