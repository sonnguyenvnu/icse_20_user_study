/** 
 * Unsafe version of  {@link #m_enableConeFriction(int) m_enableConeFriction}. 
 */
public static void nm_enableConeFriction(long struct,int value){
  UNSAFE.putInt(null,struct + B3PhysicsSimulationParameters.M_ENABLECONEFRICTION,value);
}
