/** 
 * Unsafe version of  {@link #m_contactDistance}. 
 */
public static double nm_contactDistance(long struct){
  return UNSAFE.getDouble(null,struct + B3ContactPointData.M_CONTACTDISTANCE);
}
