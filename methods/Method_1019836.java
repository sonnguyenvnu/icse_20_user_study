@Override public Span log(long currentTime,String eventName,Object payload){
  AssertUtils.isTrue(currentTime >= startTime,"current time must greater than start time");
  Map<String,Object> fields=new HashMap<String,Object>();
  fields.put(eventName,payload);
  return this.log(currentTime,fields);
}
