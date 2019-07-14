/** 
 * Unsafe version of  {@link #m_controllerEvents}. 
 */
public static B3VRControllerEvent.Buffer nm_controllerEvents(long struct){
  return B3VRControllerEvent.create(memGetAddress(struct + B3VREventsData.M_CONTROLLEREVENTS),nm_numControllerEvents(struct));
}
