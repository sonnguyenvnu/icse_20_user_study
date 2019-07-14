/** 
 * Unsafe version of  {@link #m_jointPosition(double) m_jointPosition}. 
 */
public static void nm_jointPosition(long struct,double value){
  UNSAFE.putDouble(null,struct + B3JointSensorState.M_JOINTPOSITION,value);
}
