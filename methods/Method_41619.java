/** 
 * <p> Resume (un-pause) the <code> {@link org.quartz.JobDetail}</code> with the given name. </p> <p> If any of the <code>Job</code>'s<code>Trigger</code> s missed one or more fire-times, then the <code>Trigger</code>'s misfire instruction will be applied. </p>
 */
public void resumeJob(JobKey jobKey){
synchronized (lock) {
    List<OperableTrigger> triggersOfJob=getTriggersForJob(jobKey);
    for (    OperableTrigger trigger : triggersOfJob) {
      resumeTrigger(trigger.getKey());
    }
  }
}
