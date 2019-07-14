/** 
 * Unsafe version of  {@link #m_deviceType}. 
 */
public static int nm_deviceType(long struct){
  return UNSAFE.getInt(null,struct + B3VRControllerEvent.M_DEVICETYPE);
}
