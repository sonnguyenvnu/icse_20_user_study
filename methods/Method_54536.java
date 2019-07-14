/** 
 * Unsafe version of  {@link #m_jointPosition(int) m_jointPosition}. 
 */
public static double nm_jointPosition(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3JointSensorState2.M_JOINTPOSITION + check(index,4) * 8);
}
