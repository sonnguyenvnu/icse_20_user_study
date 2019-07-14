/** 
 * Unsafe version of  {@link #m_buttonState}. 
 */
public static int nm_buttonState(long struct){
  return UNSAFE.getInt(null,struct + B3MouseEvent.M_BUTTONSTATE);
}
