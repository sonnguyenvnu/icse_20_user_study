/** 
 * Unsafe version of  {@link #m_linearFrictionForce2}. 
 */
public static double nm_linearFrictionForce2(long struct){
  return UNSAFE.getDouble(null,struct + B3ContactPointData.M_LINEARFRICTIONFORCE2);
}
