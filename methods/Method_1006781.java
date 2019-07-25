public static void dispatch(Event event){
  String key=event.getType() + event.getTag();
  TreeMap<String,EventListener.Handler> listenerMap=listenersMap.get(key);
  if (listenerMap == null)   return;
  for (  EventListener.Handler listener : listenerMap.values()) {
    if (listener != null) {
      try {
        listener.handle(event);
      }
 catch (      Exception e) {
        System.out.println("\nException, while handling event: " + event + "\n");
        e.printStackTrace();
      }
    }
  }
}
