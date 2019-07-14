/** 
 * Add all the data from the given  {@link JobDataMap} to the{@code JobDetail}'s  {@code JobDataMap}.
 * @return the updated JobBuilder
 * @see JobDetail#getJobDataMap()
 */
public JobBuilder usingJobData(JobDataMap newJobDataMap){
  jobDataMap.putAll(newJobDataMap);
  return this;
}
