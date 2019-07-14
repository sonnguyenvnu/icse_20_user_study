/** 
 * Unsafe version of  {@link #m_bodyUniqueIdA}. 
 */
public static int nm_bodyUniqueIdA(long struct){
  return UNSAFE.getInt(null,struct + B3ContactPointData.M_BODYUNIQUEIDA);
}
