/** 
 * Unsafe version of  {@link #m_solverResidualThreshold}. 
 */
public static double nm_solverResidualThreshold(long struct){
  return UNSAFE.getDouble(null,struct + B3PhysicsSimulationParameters.M_SOLVERRESIDUALTHRESHOLD);
}
