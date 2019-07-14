/** 
 * Unsafe version of  {@link #m_linearFrictionDirection1(DoubleBuffer) m_linearFrictionDirection1}. 
 */
public static void nm_linearFrictionDirection1(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3ContactPointData.M_LINEARFRICTIONDIRECTION1,value.remaining() * 8);
}
