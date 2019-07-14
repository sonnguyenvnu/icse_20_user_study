/** 
 * Unsafe version of  {@link #m_positionOnBInWS}. 
 */
public static DoubleBuffer nm_positionOnBInWS(long struct){
  return memDoubleBuffer(struct + B3ContactPointData.M_POSITIONONBINWS,3);
}
