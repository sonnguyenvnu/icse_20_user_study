/** 
 * Unsafe version of  {@link #m_jointPosition}. 
 */
public static DoubleBuffer nm_jointPosition(long struct){
  return memDoubleBuffer(struct + B3JointSensorState2.M_JOINTPOSITION,4);
}
