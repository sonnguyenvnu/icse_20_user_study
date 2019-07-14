/** 
 * Unsafe version of  {@link #m_deterministicOverlappingPairs(int) m_deterministicOverlappingPairs}. 
 */
public static void nm_deterministicOverlappingPairs(long struct,int value){
  UNSAFE.putInt(null,struct + B3PhysicsSimulationParameters.M_DETERMINISTICOVERLAPPINGPAIRS,value);
}
