/** 
 * Returns the value of the  {@code hwnd} field. 
 */
@NativeType("HWND") public long hwnd(){
  return nhwnd(address());
}
