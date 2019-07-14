/** 
 * Unsafe version of  {@link #m_mousePosX}. 
 */
public static float nm_mousePosX(long struct){
  return UNSAFE.getFloat(null,struct + B3MouseEvent.M_MOUSEPOSX);
}
