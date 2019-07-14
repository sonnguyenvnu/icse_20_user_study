/** 
 * Unsafe version of  {@link #m_localInertialPosition(DoubleBuffer) m_localInertialPosition}. 
 */
public static void nm_localInertialPosition(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3LinkState.M_LOCALINERTIALPOSITION,value.remaining() * 8);
}
