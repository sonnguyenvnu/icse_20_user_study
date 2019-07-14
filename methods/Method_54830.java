/** 
 * Unsafe version of  {@link #m_buttons(IntBuffer) m_buttons}. 
 */
public static void nm_buttons(long struct,IntBuffer value){
  if (CHECKS) {
    checkGT(value,MAX_VR_BUTTONS);
  }
  memCopy(memAddress(value),struct + B3VRControllerEvent.M_BUTTONS,value.remaining() * 4);
}
