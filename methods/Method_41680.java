/** 
 * Add the given key-value pair to the Trigger's  {@link JobDataMap}.
 * @return the updated TriggerBuilder
 * @see Trigger#getJobDataMap()
 */
public TriggerBuilder<T> usingJobData(String dataKey,String value){
  jobDataMap.put(dataKey,value);
  return this;
}
