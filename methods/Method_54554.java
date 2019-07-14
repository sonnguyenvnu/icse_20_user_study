/** 
 * Unsafe version of  {@link #m_keyState}. 
 */
public static int nm_keyState(long struct){
  return UNSAFE.getInt(null,struct + B3KeyboardEvent.M_KEYSTATE);
}
