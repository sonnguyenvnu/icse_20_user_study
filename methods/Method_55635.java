/** 
 * Like  {@link #memShortBuffer}, but returns  {@code null} if {@code address} is {@link #NULL}. 
 */
@Nullable public static ShortBuffer memShortBufferSafe(long address,int capacity){
  return address == NULL ? null : wrap(BUFFER_SHORT,address,capacity);
}
