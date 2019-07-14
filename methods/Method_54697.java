/** 
 * Unsafe version of  {@link #m_enableFileCaching(int) m_enableFileCaching}. 
 */
public static void nm_enableFileCaching(long struct,int value){
  UNSAFE.putInt(null,struct + B3PhysicsSimulationParameters.M_ENABLEFILECACHING,value);
}
