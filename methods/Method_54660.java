/** 
 * Unsafe version of  {@link #m_gravityAcceleration(int) m_gravityAcceleration}. 
 */
public static double nm_gravityAcceleration(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3PhysicsSimulationParameters.M_GRAVITYACCELERATION + check(index,3) * 8);
}
