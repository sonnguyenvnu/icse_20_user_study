/** 
 * Unsafe version of  {@link #m_jointVelocity}. 
 */
public static double nm_jointVelocity(long struct){
  return UNSAFE.getDouble(null,struct + B3JointSensorState.M_JOINTVELOCITY);
}
