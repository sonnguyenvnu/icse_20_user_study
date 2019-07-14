/** 
 * Unsafe version of  {@link #m_eventType}. 
 */
public static int nm_eventType(long struct){
  return UNSAFE.getInt(null,struct + B3MouseEvent.M_EVENTTYPE);
}
