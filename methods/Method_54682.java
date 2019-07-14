/** 
 * Unsafe version of  {@link #m_enableSAT}. 
 */
public static int nm_enableSAT(long struct){
  return UNSAFE.getInt(null,struct + B3PhysicsSimulationParameters.M_ENABLESAT);
}
