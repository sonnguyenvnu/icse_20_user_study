public static OperableTrigger newTrigger(Map<String,Object> attrMap) throws ParseException {
  CronTriggerImpl result=new CronTriggerImpl();
  result.setCronExpression((String)attrMap.get("cronExpression"));
  if (attrMap.containsKey("timeZone")) {
    result.setTimeZone(TimeZone.getTimeZone((String)attrMap.get("timeZone")));
  }
  TriggerSupport.initializeTrigger(result,attrMap);
  return result;
}
