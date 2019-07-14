/** 
 * Unsafe version of  {@link #m_worldLinearVelocity(DoubleBuffer) m_worldLinearVelocity}. 
 */
public static void nm_worldLinearVelocity(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3LinkState.M_WORLDLINEARVELOCITY,value.remaining() * 8);
}
