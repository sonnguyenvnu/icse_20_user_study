/** 
 * Set the Trigger's  {@link JobDataMap}, adding any values to it that were already set on this TriggerBuilder using any of the other 'usingJobData' methods. 
 * @return the updated TriggerBuilder
 * @see Trigger#getJobDataMap()
 */
public TriggerBuilder<T> usingJobData(JobDataMap newJobDataMap){
  for (  String dataKey : jobDataMap.keySet()) {
    newJobDataMap.put(dataKey,jobDataMap.get(dataKey));
  }
  jobDataMap=newJobDataMap;
  return this;
}
