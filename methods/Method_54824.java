/** 
 * Unsafe version of  {@link #m_controllerId(int) m_controllerId}. 
 */
public static void nm_controllerId(long struct,int value){
  UNSAFE.putInt(null,struct + B3VRControllerEvent.M_CONTROLLERID,value);
}
