/** 
 * Frees the specified array of pointers.
 * @param pointers the pointer array to free
 * @param length   the pointer array length
 */
public static void apiArrayFree(long pointers,int length){
  for (int i=length; --i >= 0; ) {
    nmemFree(memGetAddress(pointers + Integer.toUnsignedLong(i) * POINTER_SIZE));
  }
}
