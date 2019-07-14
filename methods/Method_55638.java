/** 
 * Like  {@link #memFloatBuffer}, but returns  {@code null} if {@code address} is {@link #NULL}. 
 */
@Nullable public static FloatBuffer memFloatBufferSafe(long address,int capacity){
  return address == NULL ? null : wrap(BUFFER_FLOAT,address,capacity);
}
