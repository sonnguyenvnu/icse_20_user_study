/** 
 * Publish the given event to all listeners.
 * @param event the event to publish (may be an {@link ApplicationEvent}or a payload object to be turned into a  {@link PayloadApplicationEvent})
 * @param eventType the resolved event type, if known
 * @since 4.2
 */
protected void publishEvent(Object event,ResolvableType eventType){
  Assert.notNull(event,"Event must not be null");
  if (logger.isTraceEnabled()) {
    logger.trace("Publishing event in " + getDisplayName() + ": " + event);
  }
  ApplicationEvent applicationEvent;
  if (event instanceof ApplicationEvent) {
    applicationEvent=(ApplicationEvent)event;
  }
 else {
    applicationEvent=new PayloadApplicationEvent<>(this,event);
    if (eventType == null) {
      eventType=((PayloadApplicationEvent)applicationEvent).getResolvableType();
    }
  }
  Set<ApplicationEvent> earlyApplicationEvents=getFieldValueByReflect(earlyApplicationEventsField,this);
  if (earlyApplicationEvents != null) {
    earlyApplicationEvents.add(applicationEvent);
  }
 else {
    ApplicationEventMulticaster applicationEventMulticaster=getMethodValueByReflect(getApplicationEventMulticasterMethod,this);
    applicationEventMulticaster.multicastEvent(applicationEvent,eventType);
  }
}
