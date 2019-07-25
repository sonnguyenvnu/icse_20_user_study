/** 
 * @param jobKey
 * @param jobGroup
 * @param trigerValue
 * @return
 */
@Override Trigger build(String jobKey,String jobGroup,String trigerType,String trigerValue){
  return new CronTriggerImpl(TriggerKey.triggerKey(jobKey,jobGroup),trigerValue).getTrigger();
}
