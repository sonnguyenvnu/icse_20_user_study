private String getExecutionEventsString(){
  List<HystrixEventType> executionEvents=getExecutionEvents();
  if (executionEvents == null) {
    executionEvents=Collections.emptyList();
  }
  StringBuilder message=new StringBuilder("[");
  for (  HystrixEventType executionEvent : executionEvents) {
    message.append(HystrixEventType.class.getSimpleName()).append("#").append(executionEvent.name()).append(",");
  }
  for (  SofaAsyncHystrixEvent event : events) {
    message.append(SofaAsyncHystrixEvent.class.getSimpleName()).append("#").append(event.name()).append(",");
  }
  if (message.length() > 1) {
    message.deleteCharAt(message.length() - 1);
  }
  return message.append("]").toString();
}
