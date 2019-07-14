/** 
 * Unsafe version of  {@link #m_qDofSize}. 
 */
public static int nm_qDofSize(long struct){
  return UNSAFE.getInt(null,struct + B3JointSensorState2.M_QDOFSIZE);
}
