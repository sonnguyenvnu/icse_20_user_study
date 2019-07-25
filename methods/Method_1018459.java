public static HandlerRegistration register(EventBus eventBus,Handler handler){
  return eventBus.addHandler(TYPE,handler);
}
