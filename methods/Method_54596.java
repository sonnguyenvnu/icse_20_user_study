/** 
 * Unsafe version of  {@link #m_worldAABBMax(DoubleBuffer) m_worldAABBMax}. 
 */
public static void nm_worldAABBMax(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3LinkState.M_WORLDAABBMAX,value.remaining() * 8);
}
