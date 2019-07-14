/** 
 * Unsafe version of  {@link #m_collisionFilterMode}. 
 */
public static int nm_collisionFilterMode(long struct){
  return UNSAFE.getInt(null,struct + B3PhysicsSimulationParameters.M_COLLISIONFILTERMODE);
}
