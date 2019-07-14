/** 
 * Unsafe version of  {@link #m_worldOrientation}. 
 */
public static DoubleBuffer nm_worldOrientation(long struct){
  return memDoubleBuffer(struct + B3LinkState.M_WORLDORIENTATION,4);
}
