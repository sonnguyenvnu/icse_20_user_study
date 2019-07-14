/** 
 * Creates a colormap of the specified visual type for the screen on which the specified window resides and returns the colormap ID associated with it. Note that the specified window is only used to determine the screen.
 * @param display the connection to the X server
 * @param w       the window
 * @param visual  a visual type supported on the screen. If the visual type is not one supported by the screen, a {@code BadMatch} error results.
 * @param alloc   the colormap entries to be allocated. You can pass AllocNone or AllocAll.
 */
@NativeType("Colormap") public static long XCreateColormap(@NativeType("Display *") long display,@NativeType("Window") long w,@NativeType("Visual *") Visual visual,int alloc){
  return nXCreateColormap(display,w,visual.address(),alloc);
}
