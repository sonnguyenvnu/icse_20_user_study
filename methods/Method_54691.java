/** 
 * Unsafe version of  {@link #m_useSplitImpulse(int) m_useSplitImpulse}. 
 */
public static void nm_useSplitImpulse(long struct,int value){
  UNSAFE.putInt(null,struct + B3PhysicsSimulationParameters.M_USESPLITIMPULSE,value);
}
