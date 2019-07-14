/** 
 * Unsafe version of  {@link #m_positionOnAInWS}. 
 */
public static DoubleBuffer nm_positionOnAInWS(long struct){
  return memDoubleBuffer(struct + B3ContactPointData.M_POSITIONONAINWS,3);
}
