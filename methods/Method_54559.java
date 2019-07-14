/** 
 * Sets the specified value to the  {@code m_numKeyboardEvents} field of the specified {@code struct}. 
 */
public static void nm_numKeyboardEvents(long struct,int value){
  UNSAFE.putInt(null,struct + B3KeyboardEventsData.M_NUMKEYBOARDEVENTS,value);
}
