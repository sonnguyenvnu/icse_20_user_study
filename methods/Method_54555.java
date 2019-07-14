/** 
 * Unsafe version of  {@link #m_keyCode(int) m_keyCode}. 
 */
public static void nm_keyCode(long struct,int value){
  UNSAFE.putInt(null,struct + B3KeyboardEvent.M_KEYCODE,value);
}
