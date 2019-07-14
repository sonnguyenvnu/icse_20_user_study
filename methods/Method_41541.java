/** 
 * Replace the  {@code JobDetail}'s  {@link JobDataMap} with thegiven  {@code JobDataMap}.
 * @return the updated JobBuilder
 * @see JobDetail#getJobDataMap() 
 */
public JobBuilder setJobData(JobDataMap newJobDataMap){
  jobDataMap=newJobDataMap;
  return this;
}
