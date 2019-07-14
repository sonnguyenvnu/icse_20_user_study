/** 
 * Map the given event handler to a dispatcher with the given global key. 
 */
public synchronized void recordEventHandler(String globalKey,EventHandler eventHandler){
  if (globalKey == null) {
    return;
  }
  EventHandlersWrapper eventHandlers=mEventHandlers.get(globalKey);
  if (eventHandlers == null) {
    eventHandlers=new EventHandlersWrapper();
    mEventHandlers.put(globalKey,eventHandlers);
  }
  eventHandlers.addEventHandler(eventHandler);
}
