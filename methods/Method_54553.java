/** 
 * Unsafe version of  {@link #m_keyCode}. 
 */
public static int nm_keyCode(long struct){
  return UNSAFE.getInt(null,struct + B3KeyboardEvent.M_KEYCODE);
}
