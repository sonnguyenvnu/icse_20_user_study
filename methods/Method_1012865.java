/** 
 * Interrupt task
 * @param jobKey
 * @param jobGroup
 * @return
 * @throws SchedulerException
 */
public boolean interrupt(String jobKey,String jobGroup) throws SchedulerException {
  boolean removeResult=false;
  if (StringHelper.isEmpty(jobGroup) || StringHelper.isEmpty(jobKey)) {
    return removeResult;
  }
  removeResult=scheduler.interrupt(JobKey.jobKey(jobKey,jobGroup));
  return removeResult;
}
