/** 
 * Unsafe version of  {@link #m_bodyUniqueIdA(int) m_bodyUniqueIdA}. 
 */
public static void nm_bodyUniqueIdA(long struct,int value){
  UNSAFE.putInt(null,struct + B3ContactPointData.M_BODYUNIQUEIDA,value);
}
