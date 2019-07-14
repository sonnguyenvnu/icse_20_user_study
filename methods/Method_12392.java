private List<InstanceEvent> appendToEvents(InstanceEvent event,boolean isNewEvent){
  if (!isNewEvent) {
    return this.unsavedEvents;
  }
  ArrayList<InstanceEvent> events=new ArrayList<>(this.unsavedEvents.size() + 1);
  events.addAll(this.unsavedEvents);
  events.add(event);
  return events;
}
