/** 
 * Unsafe version of  {@link #m_normalForce}. 
 */
public static double nm_normalForce(long struct){
  return UNSAFE.getDouble(null,struct + B3ContactPointData.M_NORMALFORCE);
}
