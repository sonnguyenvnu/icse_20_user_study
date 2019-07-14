/** 
 * Unsafe version of  {@link #m_frictionERP}. 
 */
public static double nm_frictionERP(long struct){
  return UNSAFE.getDouble(null,struct + B3PhysicsSimulationParameters.M_FRICTIONERP);
}
