/** 
 * Unsafe version of  {@link #m_deltaTime}. 
 */
public static double nm_deltaTime(long struct){
  return UNSAFE.getDouble(null,struct + B3PhysicsSimulationParameters.M_DELTATIME);
}
