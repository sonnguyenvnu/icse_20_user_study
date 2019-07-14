/** 
 * Unsafe version of  {@link #m_jointName(ByteBuffer) m_jointName}. 
 */
public static void nm_jointName(long struct,ByteBuffer value){
  if (CHECKS) {
    checkNT1(value);
    checkGT(value,1024);
  }
  memCopy(memAddress(value),struct + B3JointInfo.M_JOINTNAME,value.remaining());
}
