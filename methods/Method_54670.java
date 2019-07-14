/** 
 * Unsafe version of  {@link #m_enableFileCaching}. 
 */
public static int nm_enableFileCaching(long struct){
  return UNSAFE.getInt(null,struct + B3PhysicsSimulationParameters.M_ENABLEFILECACHING);
}
