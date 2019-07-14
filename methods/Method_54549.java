/** 
 * Unsafe version of  {@link #m_jointReactionForceTorque(int,double) m_jointReactionForceTorque}. 
 */
public static void nm_jointReactionForceTorque(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3JointSensorState2.M_JOINTREACTIONFORCETORQUE + check(index,6) * 8,value);
}
