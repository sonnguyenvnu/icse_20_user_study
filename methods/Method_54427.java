/** 
 * Unsafe version of  {@link #m_contactDistance(double) m_contactDistance}. 
 */
public static void nm_contactDistance(long struct,double value){
  UNSAFE.putDouble(null,struct + B3ContactPointData.M_CONTACTDISTANCE,value);
}
