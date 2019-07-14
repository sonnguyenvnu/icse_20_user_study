/** 
 * Unsafe version of  {@link #m_linearFrictionDirection2(int) m_linearFrictionDirection2}. 
 */
public static double nm_linearFrictionDirection2(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3ContactPointData.M_LINEARFRICTIONDIRECTION2 + check(index,3) * 8);
}
