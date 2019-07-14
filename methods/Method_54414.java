/** 
 * Unsafe version of  {@link #m_linearFrictionDirection2}. 
 */
public static DoubleBuffer nm_linearFrictionDirection2(long struct){
  return memDoubleBuffer(struct + B3ContactPointData.M_LINEARFRICTIONDIRECTION2,3);
}
