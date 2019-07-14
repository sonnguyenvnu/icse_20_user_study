/** 
 * Unsafe version of  {@link #m_defaultContactERP}. 
 */
public static double nm_defaultContactERP(long struct){
  return UNSAFE.getDouble(null,struct + B3PhysicsSimulationParameters.M_DEFAULTCONTACTERP);
}
