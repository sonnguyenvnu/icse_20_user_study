/** 
 * Remove entries for dispatchers that are no longer present in the tree. 
 */
public synchronized void clearUnusedEventHandlers(){
  final Iterator iterator=mEventHandlers.keySet().iterator();
  while (iterator.hasNext()) {
    final EventHandlersWrapper eventHandlersWrapper=mEventHandlers.get(iterator.next());
    if (eventHandlersWrapper == null || !eventHandlersWrapper.mUsedInCurrentTree) {
      iterator.remove();
    }
 else {
      eventHandlersWrapper.mUsedInCurrentTree=false;
    }
  }
}
