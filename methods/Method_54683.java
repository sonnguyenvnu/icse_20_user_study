/** 
 * Unsafe version of  {@link #m_constraintSolverType}. 
 */
public static int nm_constraintSolverType(long struct){
  return UNSAFE.getInt(null,struct + B3PhysicsSimulationParameters.M_CONSTRAINTSOLVERTYPE);
}
