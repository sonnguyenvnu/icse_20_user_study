/** 
 * Unsafe version of  {@link #m_enableConeFriction}. 
 */
public static int nm_enableConeFriction(long struct){
  return UNSAFE.getInt(null,struct + B3PhysicsSimulationParameters.M_ENABLECONEFRICTION);
}
