/** 
 * Unsafe version of  {@link #m_contactBreakingThreshold(double) m_contactBreakingThreshold}. 
 */
public static void nm_contactBreakingThreshold(long struct,double value){
  UNSAFE.putDouble(null,struct + B3PhysicsSimulationParameters.M_CONTACTBREAKINGTHRESHOLD,value);
}
