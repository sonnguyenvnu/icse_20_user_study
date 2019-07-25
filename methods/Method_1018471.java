public static HandlerRegistration register(EventBus eventBus,String sourceName,Handler handler){
  return eventBus.addHandlerToSource(TYPE,sourceName,handler);
}
