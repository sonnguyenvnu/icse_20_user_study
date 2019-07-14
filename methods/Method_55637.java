/** 
 * Like  {@link #memLongBuffer}, but returns  {@code null} if {@code address} is {@link #NULL}. 
 */
@Nullable public static LongBuffer memLongBufferSafe(long address,int capacity){
  return address == NULL ? null : wrap(BUFFER_LONG,address,capacity);
}
