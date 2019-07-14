public List<HystrixEventType> getOrderedList(){
  List<HystrixEventType> eventList=new ArrayList<HystrixEventType>();
  for (  HystrixEventType eventType : ALL_EVENT_TYPES) {
    if (eventCounts.contains(eventType)) {
      eventList.add(eventType);
    }
  }
  return eventList;
}
