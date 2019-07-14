/** 
 * Unsafe version of  {@link #m_contactNormalOnBInWS(int,double) m_contactNormalOnBInWS}. 
 */
public static void nm_contactNormalOnBInWS(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3ContactPointData.M_CONTACTNORMALONBINWS + check(index,3) * 8,value);
}
