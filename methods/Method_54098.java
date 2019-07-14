/** 
 * Unsafe version of  {@link #data}. 
 */
public static ByteBuffer ndata(long struct){
  return memByteBuffer(struct + AIString.DATA,(int)nlength(struct));
}
