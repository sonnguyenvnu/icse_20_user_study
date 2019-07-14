/** 
 * Unsafe version of  {@link #m_linearFrictionDirection2(DoubleBuffer) m_linearFrictionDirection2}. 
 */
public static void nm_linearFrictionDirection2(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3ContactPointData.M_LINEARFRICTIONDIRECTION2,value.remaining() * 8);
}
