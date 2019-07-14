/** 
 * Unsafe version of  {@link #m_deviceType(int) m_deviceType}. 
 */
public static void nm_deviceType(long struct,int value){
  UNSAFE.putInt(null,struct + B3VRControllerEvent.M_DEVICETYPE,value);
}
