/** 
 * Unsafe version of  {@link #m_jointAxis(DoubleBuffer) m_jointAxis}. 
 */
public static void nm_jointAxis(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3JointInfo.M_JOINTAXIS,value.remaining() * 8);
}
