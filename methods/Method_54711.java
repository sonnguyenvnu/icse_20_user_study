/** 
 * Unsafe version of  {@link #m_minimumSolverIslandSize(int) m_minimumSolverIslandSize}. 
 */
public static void nm_minimumSolverIslandSize(long struct,int value){
  UNSAFE.putInt(null,struct + B3PhysicsSimulationParameters.M_MINIMUMSOLVERISLANDSIZE,value);
}
