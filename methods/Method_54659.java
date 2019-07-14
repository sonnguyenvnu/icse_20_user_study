/** 
 * Unsafe version of  {@link #m_gravityAcceleration}. 
 */
public static DoubleBuffer nm_gravityAcceleration(long struct){
  return memDoubleBuffer(struct + B3PhysicsSimulationParameters.M_GRAVITYACCELERATION,3);
}
