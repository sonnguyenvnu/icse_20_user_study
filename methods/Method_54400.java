/** 
 * Unsafe version of  {@link #m_linkIndexA}. 
 */
public static int nm_linkIndexA(long struct){
  return UNSAFE.getInt(null,struct + B3ContactPointData.M_LINKINDEXA);
}
