/** 
 * Unsafe version of  {@link #m_worldAABBMin}. 
 */
public static DoubleBuffer nm_worldAABBMin(long struct){
  return memDoubleBuffer(struct + B3LinkState.M_WORLDAABBMIN,3);
}
