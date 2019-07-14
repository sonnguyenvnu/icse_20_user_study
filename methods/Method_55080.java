/** 
 * Returns the root window of the specified screen.
 * @param display       the connection to the X server
 * @param screen_number the appropriate screen number on the host server
 */
@NativeType("Window") public static long XRootWindow(@NativeType("Display *") long display,int screen_number){
  long __functionAddress=Functions.XRootWindow;
  if (CHECKS) {
    check(display);
  }
  return invokePP(display,screen_number,__functionAddress);
}
