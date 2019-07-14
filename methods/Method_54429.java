/** 
 * Unsafe version of  {@link #m_linearFrictionForce1(double) m_linearFrictionForce1}. 
 */
public static void nm_linearFrictionForce1(long struct,double value){
  UNSAFE.putDouble(null,struct + B3ContactPointData.M_LINEARFRICTIONFORCE1,value);
}
