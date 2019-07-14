/** 
 * Unsafe version of  {@link #lpszMenuName}. 
 */
@Nullable public static ByteBuffer nlpszMenuName(long struct){
  return memByteBufferNT2Safe(memGetAddress(struct + WNDCLASSEX.LPSZMENUNAME));
}
