/** 
 * Unsafe version of  {@link #m_solverResidualThreshold(double) m_solverResidualThreshold}. 
 */
public static void nm_solverResidualThreshold(long struct,double value){
  UNSAFE.putDouble(null,struct + B3PhysicsSimulationParameters.M_SOLVERRESIDUALTHRESHOLD,value);
}
