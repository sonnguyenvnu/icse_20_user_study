/** 
 * Unsafe version of  {@link #m_jointVelocity}. 
 */
public static DoubleBuffer nm_jointVelocity(long struct){
  return memDoubleBuffer(struct + B3JointSensorState2.M_JOINTVELOCITY,3);
}
