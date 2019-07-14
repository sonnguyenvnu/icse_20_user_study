/** 
 * Unsafe version of  {@link #m_splitImpulsePenetrationThreshold(double) m_splitImpulsePenetrationThreshold}. 
 */
public static void nm_splitImpulsePenetrationThreshold(long struct,double value){
  UNSAFE.putDouble(null,struct + B3PhysicsSimulationParameters.M_SPLITIMPULSEPENETRATIONTHRESHOLD,value);
}
