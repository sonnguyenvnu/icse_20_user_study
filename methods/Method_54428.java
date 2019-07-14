/** 
 * Unsafe version of  {@link #m_normalForce(double) m_normalForce}. 
 */
public static void nm_normalForce(long struct,double value){
  UNSAFE.putDouble(null,struct + B3ContactPointData.M_NORMALFORCE,value);
}
