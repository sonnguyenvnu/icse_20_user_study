@Override public @Nullable Object dispatchOnEvent(EventHandler eventHandler,Object eventState){
  if (ComponentsConfiguration.enableOnErrorHandling && eventHandler.id == ERROR_EVENT_HANDLER_ID) {
    ((Component)this).getErrorHandler().dispatchEvent(((ErrorEvent)eventState));
  }
  return null;
}
