/** 
 * Reraise an error event up the hierarchy so it can be caught by another component, or reach the root and cause the application to crash.
 * @param c The component context the error event was caught in.
 * @param e The original exception.
 */
public static void dispatchErrorEvent(ComponentContext c,Exception e){
  if (ComponentsConfiguration.enableOnErrorHandling) {
    final ErrorEvent errorEvent=new ErrorEvent();
    errorEvent.exception=e;
    dispatchErrorEvent(c,errorEvent);
  }
 else {
    if (e instanceof RuntimeException) {
      throw (RuntimeException)e;
    }
 else {
      throw new RuntimeException(e);
    }
  }
}
