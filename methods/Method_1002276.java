public boolean equals(IEvent event){
  return getKey().equals(event.getKey()) && mEventType.equals(event.getType());
}
