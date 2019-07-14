/** 
 * Unsafe version of  {@link #m_contactFlags}. 
 */
public static int nm_contactFlags(long struct){
  return UNSAFE.getInt(null,struct + B3ContactPointData.M_CONTACTFLAGS);
}
