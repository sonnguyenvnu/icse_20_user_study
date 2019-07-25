public void pulish(final JbootEvent event){
  String action=event.getAction();
  List<JbootEventListener> syncListeners=listenerMap.get(action);
  if (syncListeners != null && !syncListeners.isEmpty()) {
    invokeListeners(event,syncListeners);
  }
  List<JbootEventListener> listeners=asyncListenerMap.get(action);
  if (listeners != null && !listeners.isEmpty()) {
    invokeListenersAsync(event,listeners);
  }
}
