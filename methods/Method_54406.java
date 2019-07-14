/** 
 * Unsafe version of  {@link #m_contactNormalOnBInWS}. 
 */
public static DoubleBuffer nm_contactNormalOnBInWS(long struct){
  return memDoubleBuffer(struct + B3ContactPointData.M_CONTACTNORMALONBINWS,3);
}
