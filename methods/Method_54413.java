/** 
 * Unsafe version of  {@link #m_linearFrictionDirection1(int) m_linearFrictionDirection1}. 
 */
public static double nm_linearFrictionDirection1(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3ContactPointData.M_LINEARFRICTIONDIRECTION1 + check(index,3) * 8);
}
