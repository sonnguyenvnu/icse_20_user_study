/** 
 * Register additional listener.
 * @param listener the RepeatListener to be added to the list of listeners to be notified.
 */
public void register(RepeatListener listener){
  if (!listeners.contains(listener)) {
    listeners.add(listener);
  }
}
