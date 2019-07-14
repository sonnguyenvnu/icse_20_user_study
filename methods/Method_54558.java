/** 
 * Sets the address of the specified  {@link B3KeyboardEvent.Buffer} to the {@code m_keyboardEvents} field. 
 */
public B3KeyboardEventsData m_keyboardEvents(@NativeType("struct b3KeyboardEvent *") B3KeyboardEvent.Buffer value){
  nm_keyboardEvents(address(),value);
  return this;
}
