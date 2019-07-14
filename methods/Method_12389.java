Instance apply(Collection<InstanceEvent> events){
  Assert.notNull(events,"'events' must not be null");
  Instance instance=this;
  for (  InstanceEvent event : events) {
    instance=instance.apply(event);
  }
  return instance;
}
