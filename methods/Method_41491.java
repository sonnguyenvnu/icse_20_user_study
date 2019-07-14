/** 
 * <p> Called when the <code> {@link Scheduler}</code> has decided to 'fire' the trigger (execute the associated <code>Job</code>), in order to give the <code>Trigger</code> a chance to update itself for its next triggering (if any). </p>
 * @see #executionComplete(JobExecutionContext,JobExecutionException)
 */
@Override public void triggered(org.quartz.Calendar calendar){
  timesTriggered++;
  previousFireTime=nextFireTime;
  nextFireTime=getFireTimeAfter(nextFireTime);
  while (nextFireTime != null && calendar != null && !calendar.isTimeIncluded(nextFireTime.getTime())) {
    nextFireTime=getFireTimeAfter(nextFireTime);
    if (nextFireTime == null)     break;
    java.util.Calendar c=java.util.Calendar.getInstance();
    c.setTime(nextFireTime);
    if (c.get(java.util.Calendar.YEAR) > YEAR_TO_GIVEUP_SCHEDULING_AT) {
      nextFireTime=null;
    }
  }
}
