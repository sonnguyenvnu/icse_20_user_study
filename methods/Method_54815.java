/** 
 * Unsafe version of  {@link #m_controllerId}. 
 */
public static int nm_controllerId(long struct){
  return UNSAFE.getInt(null,struct + B3VRControllerEvent.M_CONTROLLERID);
}
