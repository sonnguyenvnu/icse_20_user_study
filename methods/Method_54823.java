/** 
 * Unsafe version of  {@link #m_buttons(int) m_buttons}. 
 */
public static int nm_buttons(long struct,int index){
  return UNSAFE.getInt(null,struct + B3VRControllerEvent.M_BUTTONS + check(index,MAX_VR_BUTTONS) * 4);
}
