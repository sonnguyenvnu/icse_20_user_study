/** 
 * Like  {@link #memByteBufferNT2(long) memByteBufferNT2}, but returns  {@code null} if {@code address} is {@link #NULL}. 
 */
@Nullable public static ByteBuffer memByteBufferNT2Safe(long address){
  return address == NULL ? null : memByteBufferNT2(address,Integer.MAX_VALUE - 1);
}
