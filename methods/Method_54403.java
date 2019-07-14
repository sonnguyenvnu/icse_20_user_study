/** 
 * Unsafe version of  {@link #m_positionOnAInWS(int) m_positionOnAInWS}. 
 */
public static double nm_positionOnAInWS(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3ContactPointData.M_POSITIONONAINWS + check(index,3) * 8);
}
