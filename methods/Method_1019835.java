@Override public Span log(long currentTime,String eventValue){
  AssertUtils.isTrue(currentTime >= startTime,"Current time must greater than start time");
  Map<String,String> fields=new HashMap<String,String>();
  fields.put(LogData.EVENT_TYPE_KEY,eventValue);
  return this.log(currentTime,fields);
}
