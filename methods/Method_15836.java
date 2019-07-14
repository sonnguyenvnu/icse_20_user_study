@Override @SuppressWarnings("unchecked") public void doEvent(String serverId,OAuth2Event event,Class<? extends OAuth2Event> eventType){
  listenerStore.getOrDefault(serverId,new java.util.HashMap<>()).getOrDefault(eventType,new ArrayList<>()).forEach(listener -> listener.on(event));
}
