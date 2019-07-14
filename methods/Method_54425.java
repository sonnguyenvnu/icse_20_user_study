/** 
 * Unsafe version of  {@link #m_contactNormalOnBInWS(DoubleBuffer) m_contactNormalOnBInWS}. 
 */
public static void nm_contactNormalOnBInWS(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3ContactPointData.M_CONTACTNORMALONBINWS,value.remaining() * 8);
}
