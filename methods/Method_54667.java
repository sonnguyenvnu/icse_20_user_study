/** 
 * Unsafe version of  {@link #m_internalSimFlags}. 
 */
public static int nm_internalSimFlags(long struct){
  return UNSAFE.getInt(null,struct + B3PhysicsSimulationParameters.M_INTERNALSIMFLAGS);
}
