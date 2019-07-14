/** 
 * Unsafe version of  {@link #m_controllerEvents(B3VRControllerEvent.Buffer) m_controllerEvents}. 
 */
public static void nm_controllerEvents(long struct,B3VRControllerEvent.Buffer value){
  memPutAddress(struct + B3VREventsData.M_CONTROLLEREVENTS,value.address());
  nm_numControllerEvents(struct,value.remaining());
}
