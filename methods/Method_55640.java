/** 
 * Like  {@link #memPointerBuffer}, but returns  {@code null} if {@code address} is {@link #NULL}. 
 */
@Nullable public static PointerBuffer memPointerBufferSafe(long address,int capacity){
  return address == NULL ? null : Pointer.Default.wrap(PointerBuffer.class,address,capacity);
}
