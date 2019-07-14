/** 
 * Unsafe version of  {@link #m_jointPosition}. 
 */
public static double nm_jointPosition(long struct){
  return UNSAFE.getDouble(null,struct + B3JointSensorState.M_JOINTPOSITION);
}
