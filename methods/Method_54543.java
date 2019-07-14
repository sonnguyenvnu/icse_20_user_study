/** 
 * Unsafe version of  {@link #m_uDofSize}. 
 */
public static int nm_uDofSize(long struct){
  return UNSAFE.getInt(null,struct + B3JointSensorState2.M_UDOFSIZE);
}
