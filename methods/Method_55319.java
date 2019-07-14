/** 
 * Returns the value of the  {@code lParam} field. 
 */
@NativeType("LPARAM") public long lParam(){
  return nlParam(address());
}
