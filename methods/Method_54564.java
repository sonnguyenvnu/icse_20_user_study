/** 
 * Unsafe version of  {@link #m_worldPosition}. 
 */
public static DoubleBuffer nm_worldPosition(long struct){
  return memDoubleBuffer(struct + B3LinkState.M_WORLDPOSITION,3);
}
