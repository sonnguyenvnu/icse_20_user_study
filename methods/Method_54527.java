/** 
 * Unsafe version of  {@link #m_jointVelocity(double) m_jointVelocity}. 
 */
public static void nm_jointVelocity(long struct,double value){
  UNSAFE.putDouble(null,struct + B3JointSensorState.M_JOINTVELOCITY,value);
}
