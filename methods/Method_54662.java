/** 
 * Unsafe version of  {@link #m_numSolverIterations}. 
 */
public static int nm_numSolverIterations(long struct){
  return UNSAFE.getInt(null,struct + B3PhysicsSimulationParameters.M_NUMSOLVERITERATIONS);
}
