/** 
 * Unsafe version of  {@link #m_jointForceTorque(DoubleBuffer) m_jointForceTorque}. 
 */
public static void nm_jointForceTorque(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,6);
  }
  memCopy(memAddress(value),struct + B3JointSensorState.M_JOINTFORCETORQUE,value.remaining() * 8);
}
