/** 
 * Unsafe version of  {@link #m_linearFrictionForce2(double) m_linearFrictionForce2}. 
 */
public static void nm_linearFrictionForce2(long struct,double value){
  UNSAFE.putDouble(null,struct + B3ContactPointData.M_LINEARFRICTIONFORCE2,value);
}
