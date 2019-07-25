/** 
 * Posts an event to all registered handlers.  This method will return successfully after the event has been posted to all handlers, and regardless of any exceptions thrown by handlers.
 * @param event  event to post.
 */
public void post(Object event){
  List<EventHandler> dispatching=new ArrayList<>();
  Set<Class<?>> dispatchTypes=flattenHierarchyCache.get(event.getClass());
  lock.readLock().lock();
  try {
    for (    Class<?> eventType : dispatchTypes) {
      Set<EventHandler> wrappers=handlersByType.get(eventType);
      if (wrappers != null && !wrappers.isEmpty()) {
        dispatching.addAll(wrappers);
      }
    }
  }
  finally {
    lock.readLock().unlock();
  }
  Collections.sort(dispatching);
  for (  EventHandler handler : dispatching) {
    dispatch(event,handler);
  }
}
