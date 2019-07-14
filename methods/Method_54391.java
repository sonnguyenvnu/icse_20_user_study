/** 
 * Sets the specified value to the  {@code m_numContactPoints} field of the specified {@code struct}. 
 */
public static void nm_numContactPoints(long struct,int value){
  UNSAFE.putInt(null,struct + B3ContactInformation.M_NUMCONTACTPOINTS,value);
}
