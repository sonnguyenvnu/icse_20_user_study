/** 
 * Unsafe version of  {@link #m_collisionFilterMode(int) m_collisionFilterMode}. 
 */
public static void nm_collisionFilterMode(long struct,int value){
  UNSAFE.putInt(null,struct + B3PhysicsSimulationParameters.M_COLLISIONFILTERMODE,value);
}
