/** 
 * Unsafe version of  {@link #m_numControllerEvents}. 
 */
public static int nm_numControllerEvents(long struct){
  return UNSAFE.getInt(null,struct + B3VREventsData.M_NUMCONTROLLEREVENTS);
}
