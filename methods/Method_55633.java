/** 
 * Pointer version of  {@link #memAddressSafe(ByteBuffer)}. 
 */
public static long memAddressSafe(@Nullable Pointer pointer){
  return pointer == null ? NULL : pointer.address();
}
