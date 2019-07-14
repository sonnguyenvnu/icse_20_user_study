/** 
 * Unsafe version of  {@link #m_length}. 
 */
public static int nm_length(long struct){
  return UNSAFE.getInt(null,struct + B3UserDataValue.M_LENGTH);
}
