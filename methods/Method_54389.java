/** 
 * Unsafe version of  {@link #m_numContactPoints}. 
 */
public static int nm_numContactPoints(long struct){
  return UNSAFE.getInt(null,struct + B3ContactInformation.M_NUMCONTACTPOINTS);
}
