/** 
 * Unsafe version of  {@link #m_mousePosY}. 
 */
public static float nm_mousePosY(long struct){
  return UNSAFE.getFloat(null,struct + B3MouseEvent.M_MOUSEPOSY);
}
