/** 
 * Unsafe version of  {@link #m_gravityAcceleration(DoubleBuffer) m_gravityAcceleration}. 
 */
public static void nm_gravityAcceleration(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3PhysicsSimulationParameters.M_GRAVITYACCELERATION,value.remaining() * 8);
}
