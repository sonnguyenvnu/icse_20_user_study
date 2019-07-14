/** 
 * Returns a pointer to the default screen.
 * @param display the connection to the X server
 */
public static int XDefaultScreen(@NativeType("Display *") long display){
  long __functionAddress=Functions.XDefaultScreen;
  if (CHECKS) {
    check(display);
  }
  return invokePI(display,__functionAddress);
}
