/** 
 * Unsafe version of  {@link #m_buttonState(int) m_buttonState}. 
 */
public static void nm_buttonState(long struct,int value){
  UNSAFE.putInt(null,struct + B3MouseEvent.M_BUTTONSTATE,value);
}
