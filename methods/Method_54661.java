/** 
 * Unsafe version of  {@link #m_numSimulationSubSteps}. 
 */
public static int nm_numSimulationSubSteps(long struct){
  return UNSAFE.getInt(null,struct + B3PhysicsSimulationParameters.M_NUMSIMULATIONSUBSTEPS);
}
