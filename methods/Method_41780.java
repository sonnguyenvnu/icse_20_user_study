/** 
 * Helper method for generating unique job/trigger name for the   file scanning jobs (one per FileJob).  The unique names are saved in jobTriggerNameSet.
 */
private String buildJobTriggerName(String fileBasename){
  String jobTriggerName=JOB_INITIALIZATION_PLUGIN_NAME + '_' + getName() + '_' + fileBasename.replace('.','_');
  if (jobTriggerName.length() > MAX_JOB_TRIGGER_NAME_LEN) {
    jobTriggerName=jobTriggerName.substring(0,MAX_JOB_TRIGGER_NAME_LEN);
  }
  int currentIndex=1;
  while (jobTriggerNameSet.add(jobTriggerName) == false) {
    if (currentIndex > 1) {
      jobTriggerName=jobTriggerName.substring(0,jobTriggerName.lastIndexOf('_'));
    }
    String numericSuffix="_" + currentIndex++;
    if (jobTriggerName.length() > (MAX_JOB_TRIGGER_NAME_LEN - numericSuffix.length())) {
      jobTriggerName=jobTriggerName.substring(0,(MAX_JOB_TRIGGER_NAME_LEN - numericSuffix.length()));
    }
    jobTriggerName+=numericSuffix;
  }
  return jobTriggerName;
}
