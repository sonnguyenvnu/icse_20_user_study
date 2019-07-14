/** 
 * Unsafe version of  {@link #m_worldAngularVelocity}. 
 */
public static DoubleBuffer nm_worldAngularVelocity(long struct){
  return memDoubleBuffer(struct + B3LinkState.M_WORLDANGULARVELOCITY,3);
}
