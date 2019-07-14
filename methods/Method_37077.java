/** 
 * Unregister an event subscriber
 * @param eventHandler event subscriber wrapper
 */
public synchronized void unregister(@NonNull EventHandlerWrapper eventHandler){
  String type=eventHandler.type;
  List<EventHandlerWrapper> handlers=subscribers.get(type);
  if (handlers != null) {
    handlers.remove(eventHandler);
  }
}
