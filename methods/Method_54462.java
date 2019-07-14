/** 
 * Unsafe version of  {@link #m_localInertialDiagonal(DoubleBuffer) m_localInertialDiagonal}. 
 */
public static void nm_localInertialDiagonal(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3DynamicsInfo.M_LOCALINERTIALDIAGONAL,value.remaining() * 8);
}
