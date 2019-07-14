/** 
 * Unsafe version of  {@link #m_positionOnAInWS(int,double) m_positionOnAInWS}. 
 */
public static void nm_positionOnAInWS(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3ContactPointData.M_POSITIONONAINWS + check(index,3) * 8,value);
}
