public void destroy(Subscriber subscriber){
  final String groupAndSubject=subscriber.name();
  if (!subscribers.containsKey(groupAndSubject)) {
    return;
  }
  final ConcurrentMap<String,Subscriber> m=subscribers.get(groupAndSubject);
  if (m == null) {
    return;
  }
  m.remove(subscriber.getConsumerId());
  if (m.isEmpty()) {
    handleGroupOffline(subscriber);
  }
}
