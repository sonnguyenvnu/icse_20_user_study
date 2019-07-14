/** 
 * Unsafe version of  {@link #m_positionOnBInWS(DoubleBuffer) m_positionOnBInWS}. 
 */
public static void nm_positionOnBInWS(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3ContactPointData.M_POSITIONONBINWS,value.remaining() * 8);
}
