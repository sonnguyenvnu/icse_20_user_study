/** 
 * Unsafe version of  {@link #m_positionOnBInWS(int) m_positionOnBInWS}. 
 */
public static double nm_positionOnBInWS(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3ContactPointData.M_POSITIONONBINWS + check(index,3) * 8);
}
