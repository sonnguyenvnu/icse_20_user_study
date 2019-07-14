/** 
 * Unsafe version of  {@link #m_contactSlop}. 
 */
public static double nm_contactSlop(long struct){
  return UNSAFE.getDouble(null,struct + B3PhysicsSimulationParameters.M_CONTACTSLOP);
}
