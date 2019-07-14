public static OperableTrigger newTrigger(CompositeData cData) throws ParseException {
  CronTriggerImpl result=new CronTriggerImpl();
  result.setCronExpression((String)cData.get("cronExpression"));
  if (cData.containsKey("timeZone")) {
    result.setTimeZone(TimeZone.getTimeZone((String)cData.get("timeZone")));
  }
  TriggerSupport.initializeTrigger(result,cData);
  return result;
}
