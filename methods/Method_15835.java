@Override @SuppressWarnings("unchecked") public void registerListener(String serverId,OAuth2Listener<? extends OAuth2Event> listener){
  Class type=ClassUtils.getGenericType(listener.getClass());
  listenerStore.computeIfAbsent(serverId,k -> new HashMap<>()).computeIfAbsent(type,k -> new ArrayList<>()).add(listener);
}
