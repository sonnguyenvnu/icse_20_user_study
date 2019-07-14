/** 
 * Unsafe version of  {@link #m_keyboardEvents(B3KeyboardEvent.Buffer) m_keyboardEvents}. 
 */
public static void nm_keyboardEvents(long struct,B3KeyboardEvent.Buffer value){
  memPutAddress(struct + B3KeyboardEventsData.M_KEYBOARDEVENTS,value.address());
  nm_numKeyboardEvents(struct,value.remaining());
}
