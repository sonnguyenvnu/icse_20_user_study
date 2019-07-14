/** 
 * Like  {@link #memDoubleBuffer}, but returns  {@code null} if {@code address} is {@link #NULL}. 
 */
@Nullable public static DoubleBuffer memDoubleBufferSafe(long address,int capacity){
  return address == NULL ? null : wrap(BUFFER_DOUBLE,address,capacity);
}
