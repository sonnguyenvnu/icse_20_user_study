/** 
 * Unsafe version of  {@link #m_buttons(int,int) m_buttons}. 
 */
public static void nm_buttons(long struct,int index,int value){
  UNSAFE.putInt(null,struct + B3VRControllerEvent.M_BUTTONS + check(index,MAX_VR_BUTTONS) * 4,value);
}
