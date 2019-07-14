/** 
 * Unsafe version of  {@link #m_worldAABBMax}. 
 */
public static DoubleBuffer nm_worldAABBMax(long struct){
  return memDoubleBuffer(struct + B3LinkState.M_WORLDAABBMAX,3);
}
