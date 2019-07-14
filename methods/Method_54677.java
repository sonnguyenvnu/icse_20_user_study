/** 
 * Unsafe version of  {@link #m_deterministicOverlappingPairs}. 
 */
public static int nm_deterministicOverlappingPairs(long struct){
  return UNSAFE.getInt(null,struct + B3PhysicsSimulationParameters.M_DETERMINISTICOVERLAPPINGPAIRS);
}
