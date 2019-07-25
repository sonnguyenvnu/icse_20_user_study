@Override public void register(EventHandler eventHandler){
  CopyOnWriteArraySet<EventHandler> set=SUBSCRIBER_MAP.get(eventHandler.getClass().getClassLoader());
  if (set == null) {
    set=new CopyOnWriteArraySet<EventHandler>();
    CopyOnWriteArraySet<EventHandler> old=SUBSCRIBER_MAP.putIfAbsent(eventHandler.getClass().getClassLoader(),set);
    if (old != null) {
      set=old;
    }
  }
  set.add(eventHandler);
  LOGGER.debug(String.format("Register event handler: %s.",eventHandler));
}
