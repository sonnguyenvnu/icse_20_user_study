/** 
 * Unsafe version of  {@link #m_linearFrictionDirection1}. 
 */
public static DoubleBuffer nm_linearFrictionDirection1(long struct){
  return memDoubleBuffer(struct + B3ContactPointData.M_LINEARFRICTIONDIRECTION1,3);
}
