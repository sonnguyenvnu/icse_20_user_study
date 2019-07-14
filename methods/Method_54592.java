/** 
 * Unsafe version of  {@link #m_worldAngularVelocity(DoubleBuffer) m_worldAngularVelocity}. 
 */
public static void nm_worldAngularVelocity(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3LinkState.M_WORLDANGULARVELOCITY,value.remaining() * 8);
}
