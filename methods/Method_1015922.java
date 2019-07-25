/** 
 * The lifecycle pointcut for the plugin to close(disable status).
 */
protected void stop(){
  final EventManager eventManager=BeanManager.getInstance().getReference(EventManager.class);
  for (  final AbstractEventListener<?> eventListener : eventListeners) {
    eventManager.unregisterListener(eventListener);
  }
}
