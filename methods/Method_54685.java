/** 
 * Unsafe version of  {@link #m_deltaTime(double) m_deltaTime}. 
 */
public static void nm_deltaTime(long struct,double value){
  UNSAFE.putDouble(null,struct + B3PhysicsSimulationParameters.M_DELTATIME,value);
}
