/** 
 * Unsafe version of  {@link #m_worldAABBMin(DoubleBuffer) m_worldAABBMin}. 
 */
public static void nm_worldAABBMin(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3LinkState.M_WORLDAABBMIN,value.remaining() * 8);
}
