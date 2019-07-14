/** 
 * Unsafe version of  {@link #m_frictionCFM}. 
 */
public static double nm_frictionCFM(long struct){
  return UNSAFE.getDouble(null,struct + B3PhysicsSimulationParameters.M_FRICTIONCFM);
}
