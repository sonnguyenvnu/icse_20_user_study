/** 
 * Unsafe version of  {@link #m_jointReactionForceTorque}. 
 */
public static DoubleBuffer nm_jointReactionForceTorque(long struct){
  return memDoubleBuffer(struct + B3JointSensorState2.M_JOINTREACTIONFORCETORQUE,6);
}
