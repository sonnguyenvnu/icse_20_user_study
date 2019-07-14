/** 
 * Unsafe version of  {@link #m_jointVelocity(int) m_jointVelocity}. 
 */
public static double nm_jointVelocity(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3JointSensorState2.M_JOINTVELOCITY + check(index,3) * 8);
}
