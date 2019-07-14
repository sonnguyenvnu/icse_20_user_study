/** 
 * Unsafe version of  {@link #value}. 
 */
public static ByteBuffer nvalue(long struct){
  return memByteBufferNT1(memGetAddress(struct + ObjCPropertyAttribute.VALUE));
}
