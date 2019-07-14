/** 
 * For internal use, only. Use  {@link #dispatchErrorEvent(ComponentContext,Exception)} instead.
 */
public static void dispatchErrorEvent(ComponentContext c,ErrorEvent e){
  final EventHandler<ErrorEvent> errorHandler=c.getComponentScope().getErrorHandler();
  if (errorHandler != null) {
    errorHandler.dispatchEvent(e);
  }
}
