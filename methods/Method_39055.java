/** 
 * Invoke the listener based on type. Not very OOP, but works.
 */
public static void invoke(final Object listener,final Class listenerType){
  if (listenerType == Init.class) {
    ((Init)listener).init();
    return;
  }
  if (listenerType == Start.class) {
    ((Start)listener).start();
    return;
  }
  if (listenerType == Ready.class) {
    ((Ready)listener).ready();
    return;
  }
  if (listenerType == Stop.class) {
    ((Stop)listener).stop();
    return;
  }
  throw new MadvocException("Invalid listener");
}
