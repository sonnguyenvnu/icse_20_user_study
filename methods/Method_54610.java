/** 
 * Unsafe version of  {@link #m_mouseEvents(B3MouseEvent.Buffer) m_mouseEvents}. 
 */
public static void nm_mouseEvents(long struct,B3MouseEvent.Buffer value){
  memPutAddress(struct + B3MouseEventsData.M_MOUSEEVENTS,value.address());
  nm_numMouseEvents(struct,value.remaining());
}
