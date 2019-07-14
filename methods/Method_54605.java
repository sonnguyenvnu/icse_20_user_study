/** 
 * Unsafe version of  {@link #m_eventType(int) m_eventType}. 
 */
public static void nm_eventType(long struct,int value){
  UNSAFE.putInt(null,struct + B3MouseEvent.M_EVENTTYPE,value);
}
