/** 
 * Dispatches  {@code event} to the handler in {@code handler}.
 * @param event  event to dispatch.
 * @param handler  handler that will call the handler.
 */
private void dispatch(Object event,EventHandler handler){
  try {
    handler.handleEvent(event);
  }
 catch (  InvocationTargetException e) {
    logger.error("Could not dispatch event: " + event + " to handler " + handler,e);
  }
}
