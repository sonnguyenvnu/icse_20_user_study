/** 
 * Unsafe version of  {@link #m_jointReactionForceTorque(DoubleBuffer) m_jointReactionForceTorque}. 
 */
public static void nm_jointReactionForceTorque(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,6);
  }
  memCopy(memAddress(value),struct + B3JointSensorState2.M_JOINTREACTIONFORCETORQUE,value.remaining() * 8);
}
