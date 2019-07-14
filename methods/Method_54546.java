/** 
 * Unsafe version of  {@link #m_jointVelocity(DoubleBuffer) m_jointVelocity}. 
 */
public static void nm_jointVelocity(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3JointSensorState2.M_JOINTVELOCITY,value.remaining() * 8);
}
