/** 
 * Unsafe version of  {@link #m_localInertialFrame(DoubleBuffer) m_localInertialFrame}. 
 */
public static void nm_localInertialFrame(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,7);
  }
  memCopy(memAddress(value),struct + B3DynamicsInfo.M_LOCALINERTIALFRAME,value.remaining() * 8);
}
