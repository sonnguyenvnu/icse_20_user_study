/** 
 * Unsafe version of  {@link #m_linkName(ByteBuffer) m_linkName}. 
 */
public static void nm_linkName(long struct,ByteBuffer value){
  if (CHECKS) {
    checkNT1(value);
    checkGT(value,1024);
  }
  memCopy(memAddress(value),struct + B3JointInfo.M_LINKNAME,value.remaining());
}
