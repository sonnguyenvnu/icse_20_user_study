/** 
 * Unsafe version of  {@link #m_worldOrientation(DoubleBuffer) m_worldOrientation}. 
 */
public static void nm_worldOrientation(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,4);
  }
  memCopy(memAddress(value),struct + B3LinkState.M_WORLDORIENTATION,value.remaining() * 8);
}
