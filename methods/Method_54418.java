/** 
 * Unsafe version of  {@link #m_bodyUniqueIdB(int) m_bodyUniqueIdB}. 
 */
public static void nm_bodyUniqueIdB(long struct,int value){
  UNSAFE.putInt(null,struct + B3ContactPointData.M_BODYUNIQUEIDB,value);
}
