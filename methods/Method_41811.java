public ToolkitStore<String,Calendar> getOrCreateCalendarWrapperMap(){
  String calendarWrapperName=generateName(CALENDAR_WRAPPER_MAP_PREFIX);
  ToolkitStore<String,Calendar> temp=createStore(calendarWrapperName);
  calendarWrapperMapReference.compareAndSet(null,temp);
  return calendarWrapperMapReference.get();
}
