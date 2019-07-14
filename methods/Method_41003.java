public static OperableTrigger newTrigger(CompositeData cData) throws ParseException {
  SimpleTriggerImpl result=new SimpleTriggerImpl();
  result.setRepeatCount(((Integer)cData.get("repeatCount")).intValue());
  result.setRepeatInterval(((Long)cData.get("repeatInterval")).longValue());
  result.setTimesTriggered(((Integer)cData.get("timesTriggered")).intValue());
  TriggerSupport.initializeTrigger(result,cData);
  return result;
}
