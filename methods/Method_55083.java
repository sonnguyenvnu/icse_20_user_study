/** 
 * Deletes the association between the  {@code colormap} resource ID and the {@code colormap} and frees the {@code colormap} storage. However, this functionhas no effect on the default colormap for a screen. If the specified  {@code colormap} is an installed map for a screen, it is uninstalled. If thespecified  {@code colormap} is defined as the {@code colormap} for a window, {@code XFreeColormap()} changes the colormap associated with the window to{@link #None} and generates a {@code ColormapNotify} event. X does not define the colors displayed for a window with a colormap of {@link #None}.
 * @param display  the connection to the X server
 * @param colormap the colormap to destroy
 */
public static int XFreeColormap(@NativeType("Display *") long display,@NativeType("Colormap") long colormap){
  long __functionAddress=Functions.XFreeColormap;
  if (CHECKS) {
    check(display);
  }
  return invokePPI(display,colormap,__functionAddress);
}
