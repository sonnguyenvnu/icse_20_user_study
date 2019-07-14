/** 
 * Unsafe version of  {@link #m_bodyUniqueIdB}. 
 */
public static int nm_bodyUniqueIdB(long struct){
  return UNSAFE.getInt(null,struct + B3ContactPointData.M_BODYUNIQUEIDB);
}
