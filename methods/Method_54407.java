/** 
 * Unsafe version of  {@link #m_contactNormalOnBInWS(int) m_contactNormalOnBInWS}. 
 */
public static double nm_contactNormalOnBInWS(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3ContactPointData.M_CONTACTNORMALONBINWS + check(index,3) * 8);
}
