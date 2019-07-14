/** 
 * Unsafe version of  {@link #m_analogAxis}. 
 */
public static float nm_analogAxis(long struct){
  return UNSAFE.getFloat(null,struct + B3VRControllerEvent.M_ANALOGAXIS);
}
