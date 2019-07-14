/** 
 * Unsafe version of  {@link #m_linkIndexB}. 
 */
public static int nm_linkIndexB(long struct){
  return UNSAFE.getInt(null,struct + B3ContactPointData.M_LINKINDEXB);
}
