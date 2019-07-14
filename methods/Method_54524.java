/** 
 * Unsafe version of  {@link #m_jointForceTorque(int) m_jointForceTorque}. 
 */
public static double nm_jointForceTorque(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3JointSensorState.M_JOINTFORCETORQUE + check(index,6) * 8);
}
