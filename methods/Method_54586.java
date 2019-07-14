/** 
 * Unsafe version of  {@link #m_localInertialOrientation(DoubleBuffer) m_localInertialOrientation}. 
 */
public static void nm_localInertialOrientation(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,4);
  }
  memCopy(memAddress(value),struct + B3LinkState.M_LOCALINERTIALORIENTATION,value.remaining() * 8);
}
