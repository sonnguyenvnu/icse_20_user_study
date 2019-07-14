/** 
 * Unsafe version of  {@link #m_allowedCcdPenetration}. 
 */
public static double nm_allowedCcdPenetration(long struct){
  return UNSAFE.getDouble(null,struct + B3PhysicsSimulationParameters.M_ALLOWEDCCDPENETRATION);
}
