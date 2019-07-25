/** 
 * Register a  {@link GaeAuthenticationFailureEvent.Handler} on an{@link EventBus}.
 * @param eventBus the {@link EventBus}
 * @param handler a {@link GaeAuthenticationFailureEvent.Handler}
 * @return a {@link HandlerRegistration} instance
 */
public static HandlerRegistration register(EventBus eventBus,GaeAuthenticationFailureEvent.Handler handler){
  return eventBus.addHandler(TYPE,handler);
}
