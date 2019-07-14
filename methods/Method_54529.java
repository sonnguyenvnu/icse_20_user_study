/** 
 * Unsafe version of  {@link #m_jointForceTorque(int,double) m_jointForceTorque}. 
 */
public static void nm_jointForceTorque(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3JointSensorState.M_JOINTFORCETORQUE + check(index,6) * 8,value);
}
