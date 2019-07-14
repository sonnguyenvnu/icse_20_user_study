/** 
 * Returns the value of the  {@code wParam} field. 
 */
@NativeType("WPARAM") public long wParam(){
  return nwParam(address());
}
