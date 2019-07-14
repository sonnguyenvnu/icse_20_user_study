private String getEventString(EventTime eventTime,String eventName,String eventDescription){
  return eventName + " [" + getEventTimeString(eventTime) + ", " + eventDescription + "]";
}
