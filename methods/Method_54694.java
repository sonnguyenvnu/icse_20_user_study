/** 
 * Unsafe version of  {@link #m_internalSimFlags(int) m_internalSimFlags}. 
 */
public static void nm_internalSimFlags(long struct,int value){
  UNSAFE.putInt(null,struct + B3PhysicsSimulationParameters.M_INTERNALSIMFLAGS,value);
}
