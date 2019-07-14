/** 
 * Unsafe version of  {@link #m_jointPosition(DoubleBuffer) m_jointPosition}. 
 */
public static void nm_jointPosition(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,4);
  }
  memCopy(memAddress(value),struct + B3JointSensorState2.M_JOINTPOSITION,value.remaining() * 8);
}
