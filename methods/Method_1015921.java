/** 
 * The lifecycle pointcut for the plugin to start(enable status).
 */
protected void start(){
  final EventManager eventManager=BeanManager.getInstance().getReference(EventManager.class);
  for (  final AbstractEventListener<?> eventListener : eventListeners) {
    eventManager.registerListener(eventListener);
  }
}
