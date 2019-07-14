/** 
 * Unsafe version of  {@link #m_numSolverIterations(int) m_numSolverIterations}. 
 */
public static void nm_numSolverIterations(long struct,int value){
  UNSAFE.putInt(null,struct + B3PhysicsSimulationParameters.M_NUMSOLVERITERATIONS,value);
}
