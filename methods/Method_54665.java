/** 
 * Unsafe version of  {@link #m_splitImpulsePenetrationThreshold}. 
 */
public static double nm_splitImpulsePenetrationThreshold(long struct){
  return UNSAFE.getDouble(null,struct + B3PhysicsSimulationParameters.M_SPLITIMPULSEPENETRATIONTHRESHOLD);
}
