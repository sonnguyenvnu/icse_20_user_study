/** 
 * Unsafe version of  {@link #m_childFrame(DoubleBuffer) m_childFrame}. 
 */
public static void nm_childFrame(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,7);
  }
  memCopy(memAddress(value),struct + B3JointInfo.M_CHILDFRAME,value.remaining() * 8);
}
