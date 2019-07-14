/** 
 * Unsafe version of  {@link #m_worldLinearVelocity}. 
 */
public static DoubleBuffer nm_worldLinearVelocity(long struct){
  return memDoubleBuffer(struct + B3LinkState.M_WORLDLINEARVELOCITY,3);
}
