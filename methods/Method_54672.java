/** 
 * Unsafe version of  {@link #m_defaultNonContactERP}. 
 */
public static double nm_defaultNonContactERP(long struct){
  return UNSAFE.getDouble(null,struct + B3PhysicsSimulationParameters.M_DEFAULTNONCONTACTERP);
}
