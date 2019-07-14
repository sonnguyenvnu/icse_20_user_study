/** 
 * ?????????.
 * @return ???????
 */
public JobTriggerListener newJobTriggerListener(){
  return new JobTriggerListener(executionService,shardingService);
}
