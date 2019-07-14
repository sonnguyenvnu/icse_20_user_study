/** 
 * Unsafe version of  {@link #m_buttonIndex}. 
 */
public static int nm_buttonIndex(long struct){
  return UNSAFE.getInt(null,struct + B3MouseEvent.M_BUTTONINDEX);
}
