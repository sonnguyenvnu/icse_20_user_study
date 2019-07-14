/** 
 * Returns a  {@link B3VRControllerEvent.Buffer} view of the struct array pointed to by the {@code m_controllerEvents} field. 
 */
@NativeType("struct b3VRControllerEvent *") public B3VRControllerEvent.Buffer m_controllerEvents(){
  return nm_controllerEvents(address());
}
