/** 
 * Unsafe version of  {@link #m_positionOnBInWS(int,double) m_positionOnBInWS}. 
 */
public static void nm_positionOnBInWS(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3ContactPointData.M_POSITIONONBINWS + check(index,3) * 8,value);
}
