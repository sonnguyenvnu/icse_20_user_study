/** 
 * Unsafe version of  {@link #m_linearFrictionForce1}. 
 */
public static double nm_linearFrictionForce1(long struct){
  return UNSAFE.getDouble(null,struct + B3ContactPointData.M_LINEARFRICTIONFORCE1);
}
