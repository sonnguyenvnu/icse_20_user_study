/** 
 * Unsafe version of  {@link #m_contactBreakingThreshold}. 
 */
public static double nm_contactBreakingThreshold(long struct){
  return UNSAFE.getDouble(null,struct + B3PhysicsSimulationParameters.M_CONTACTBREAKINGTHRESHOLD);
}
