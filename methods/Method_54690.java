/** 
 * Unsafe version of  {@link #m_useRealTimeSimulation(int) m_useRealTimeSimulation}. 
 */
public static void nm_useRealTimeSimulation(long struct,int value){
  UNSAFE.putInt(null,struct + B3PhysicsSimulationParameters.M_USEREALTIMESIMULATION,value);
}
