/** 
 * Adds a listener to the event dispatcher. 
 */
public void addListener(Handler handler,T eventListener){
  Assertions.checkArgument(handler != null && eventListener != null);
  removeListener(eventListener);
  listeners.add(new HandlerAndListener<>(handler,eventListener));
}
