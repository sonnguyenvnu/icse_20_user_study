/** 
 * Unsafe version of  {@link #m_keyState(int) m_keyState}. 
 */
public static void nm_keyState(long struct,int value){
  UNSAFE.putInt(null,struct + B3KeyboardEvent.M_KEYSTATE,value);
}
