/** 
 * Destroys the specified window as well as all of its subwindows and causes the X server to generate a  {@code DestroyNotify} event for each window. Thewindow should never be referenced again. If the window specified by the  {@code w} argument is mapped, it is unmapped automatically. The ordering of the{@code DestroyNotify} events is such that for any given window being destroyed, {@code DestroyNotify} is generated on any inferiors of the window beforebeing generated on the window itself. The ordering among siblings and across subhierarchies is not otherwise constrained. If the window you specified is a root window, no windows are destroyed. Destroying a mapped window will generate  {@code Expose} events on other windows that were obscured by thewindow being destroyed.
 * @param display the connection to the X server
 * @param w       the window
 */
public static int XDestroyWindow(@NativeType("Display *") long display,@NativeType("Window") long w){
  long __functionAddress=Functions.XDestroyWindow;
  if (CHECKS) {
    check(display);
  }
  return invokePPI(display,w,__functionAddress);
}
