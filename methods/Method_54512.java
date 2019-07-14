/** 
 * Unsafe version of  {@link #m_parentFrame(DoubleBuffer) m_parentFrame}. 
 */
public static void nm_parentFrame(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,7);
  }
  memCopy(memAddress(value),struct + B3JointInfo.M_PARENTFRAME,value.remaining() * 8);
}
