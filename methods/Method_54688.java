/** 
 * Unsafe version of  {@link #m_numSimulationSubSteps(int) m_numSimulationSubSteps}. 
 */
public static void nm_numSimulationSubSteps(long struct,int value){
  UNSAFE.putInt(null,struct + B3PhysicsSimulationParameters.M_NUMSIMULATIONSUBSTEPS,value);
}
