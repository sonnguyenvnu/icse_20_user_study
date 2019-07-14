/** 
 * Unsafe version of  {@link #m_jointReactionForceTorque(int) m_jointReactionForceTorque}. 
 */
public static double nm_jointReactionForceTorque(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3JointSensorState2.M_JOINTREACTIONFORCETORQUE + check(index,6) * 8);
}
