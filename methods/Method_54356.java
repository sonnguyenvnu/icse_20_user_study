/** 
 * Unsafe version of  {@link #m_baseName}. 
 */
public static ByteBuffer nm_baseName(long struct){
  return memByteBuffer(struct + B3BodyInfo.M_BASENAME,1024);
}
