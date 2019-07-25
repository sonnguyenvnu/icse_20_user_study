/** 
 * Start to monitor given handle object for becoming weakly reachable. When the handle isn't used anymore, the given listener will be called.
 * @param handle the object that will be monitored
 * @param listener the listener that will be called upon release of the handle
 */
public static void monitor(Object handle,ReleaseListener listener){
  if (logger.isDebugEnabled()) {
    logger.debug("Monitoring handle [" + handle + "] with release listener [" + listener + "]");
  }
  WeakReference<Object> weakRef=new WeakReference<Object>(handle,handleQueue);
  addEntry(weakRef,listener);
}
