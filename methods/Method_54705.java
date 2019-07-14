/** 
 * Unsafe version of  {@link #m_allowedCcdPenetration(double) m_allowedCcdPenetration}. 
 */
public static void nm_allowedCcdPenetration(long struct,double value){
  UNSAFE.putDouble(null,struct + B3PhysicsSimulationParameters.M_ALLOWEDCCDPENETRATION,value);
}
