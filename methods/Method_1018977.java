/** 
 * Release this component.
 */
public final void release(){
  eventListenerList.clear();
  removeNativeEventListener();
}
