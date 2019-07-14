/** 
 * Unsafe version of  {@link #m_jointForceTorque}. 
 */
public static DoubleBuffer nm_jointForceTorque(long struct){
  return memDoubleBuffer(struct + B3JointSensorState.M_JOINTFORCETORQUE,6);
}
