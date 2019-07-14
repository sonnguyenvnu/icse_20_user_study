/** 
 * Sets the specified value to the  {@code m_numControllerEvents} field of the specified {@code struct}. 
 */
public static void nm_numControllerEvents(long struct,int value){
  UNSAFE.putInt(null,struct + B3VREventsData.M_NUMCONTROLLEREVENTS,value);
}
