/** 
 * Unsafe version of  {@link #m_positionOnAInWS(DoubleBuffer) m_positionOnAInWS}. 
 */
public static void nm_positionOnAInWS(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3ContactPointData.M_POSITIONONAINWS,value.remaining() * 8);
}
