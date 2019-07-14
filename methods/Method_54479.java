/** 
 * Unsafe version of  {@link #m_linkName}. 
 */
public static ByteBuffer nm_linkName(long struct){
  return memByteBuffer(struct + B3JointInfo.M_LINKNAME,1024);
}
