/** 
 * Unsafe version of  {@link #m_constraintSolverType(int) m_constraintSolverType}. 
 */
public static void nm_constraintSolverType(long struct,int value){
  UNSAFE.putInt(null,struct + B3PhysicsSimulationParameters.M_CONSTRAINTSOLVERTYPE,value);
}
