/** 
 * Add the given key-value pair to the JobDetail's  {@link JobDataMap}.
 * @return the updated JobBuilder
 * @see JobDetail#getJobDataMap()
 */
public JobBuilder usingJobData(String dataKey,Boolean value){
  jobDataMap.put(dataKey,value);
  return this;
}
