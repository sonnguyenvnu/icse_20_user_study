/** 
 * Unsafe version of  {@link #m_mousePosY(float) m_mousePosY}. 
 */
public static void nm_mousePosY(long struct,float value){
  UNSAFE.putFloat(null,struct + B3MouseEvent.M_MOUSEPOSY,value);
}
