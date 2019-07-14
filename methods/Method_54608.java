/** 
 * Unsafe version of  {@link #m_buttonIndex(int) m_buttonIndex}. 
 */
public static void nm_buttonIndex(long struct,int value){
  UNSAFE.putInt(null,struct + B3MouseEvent.M_BUTTONINDEX,value);
}
