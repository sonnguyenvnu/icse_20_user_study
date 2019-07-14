/** 
 * Unsafe version of  {@link #m_mousePosX(float) m_mousePosX}. 
 */
public static void nm_mousePosX(long struct,float value){
  UNSAFE.putFloat(null,struct + B3MouseEvent.M_MOUSEPOSX,value);
}
