/** 
 * Unsafe version of  {@link #m_defaultGlobalCFM}. 
 */
public static double nm_defaultGlobalCFM(long struct){
  return UNSAFE.getDouble(null,struct + B3PhysicsSimulationParameters.M_DEFAULTGLOBALCFM);
}
