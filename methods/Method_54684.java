/** 
 * Unsafe version of  {@link #m_minimumSolverIslandSize}. 
 */
public static int nm_minimumSolverIslandSize(long struct){
  return UNSAFE.getInt(null,struct + B3PhysicsSimulationParameters.M_MINIMUMSOLVERISLANDSIZE);
}
