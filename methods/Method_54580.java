/** 
 * Unsafe version of  {@link #m_worldPosition(DoubleBuffer) m_worldPosition}. 
 */
public static void nm_worldPosition(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3LinkState.M_WORLDPOSITION,value.remaining() * 8);
}
