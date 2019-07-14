/** 
 * Unsafe version of  {@link #m_useRealTimeSimulation}. 
 */
public static int nm_useRealTimeSimulation(long struct){
  return UNSAFE.getInt(null,struct + B3PhysicsSimulationParameters.M_USEREALTIMESIMULATION);
}
