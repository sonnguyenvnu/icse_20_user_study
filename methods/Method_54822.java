/** 
 * Unsafe version of  {@link #m_buttons}. 
 */
public static IntBuffer nm_buttons(long struct){
  return memIntBuffer(struct + B3VRControllerEvent.M_BUTTONS,MAX_VR_BUTTONS);
}
