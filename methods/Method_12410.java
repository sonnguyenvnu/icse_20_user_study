protected static long getLastVersion(List<InstanceEvent> events){
  return events.isEmpty() ? -1 : events.get(events.size() - 1).getVersion();
}
