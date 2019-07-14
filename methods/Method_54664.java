/** 
 * Unsafe version of  {@link #m_useSplitImpulse}. 
 */
public static int nm_useSplitImpulse(long struct){
  return UNSAFE.getInt(null,struct + B3PhysicsSimulationParameters.M_USESPLITIMPULSE);
}
