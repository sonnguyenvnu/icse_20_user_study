/** 
 * Unsafe version of  {@link #m_enableSAT(int) m_enableSAT}. 
 */
public static void nm_enableSAT(long struct,int value){
  UNSAFE.putInt(null,struct + B3PhysicsSimulationParameters.M_ENABLESAT,value);
}
