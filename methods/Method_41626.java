/** 
 * <p> Inform the <code>JobStore</code> that the scheduler is now firing the given <code>Trigger</code> (executing its associated <code>Job</code>), that it had previously acquired (reserved). </p>
 */
public List<TriggerFiredResult> triggersFired(List<OperableTrigger> firedTriggers){
synchronized (lock) {
    List<TriggerFiredResult> results=new ArrayList<TriggerFiredResult>();
    for (    OperableTrigger trigger : firedTriggers) {
      TriggerWrapper tw=triggersByKey.get(trigger.getKey());
      if (tw == null || tw.trigger == null) {
        continue;
      }
      if (tw.state != TriggerWrapper.STATE_ACQUIRED) {
        continue;
      }
      Calendar cal=null;
      if (tw.trigger.getCalendarName() != null) {
        cal=retrieveCalendar(tw.trigger.getCalendarName());
        if (cal == null)         continue;
      }
      Date prevFireTime=trigger.getPreviousFireTime();
      timeTriggers.remove(tw);
      tw.trigger.triggered(cal);
      trigger.triggered(cal);
      tw.state=TriggerWrapper.STATE_WAITING;
      TriggerFiredBundle bndle=new TriggerFiredBundle(retrieveJob(tw.jobKey),trigger,cal,false,new Date(),trigger.getPreviousFireTime(),prevFireTime,trigger.getNextFireTime());
      JobDetail job=bndle.getJobDetail();
      if (job.isConcurrentExectionDisallowed()) {
        ArrayList<TriggerWrapper> trigs=getTriggerWrappersForJob(job.getKey());
        for (        TriggerWrapper ttw : trigs) {
          if (ttw.state == TriggerWrapper.STATE_WAITING) {
            ttw.state=TriggerWrapper.STATE_BLOCKED;
          }
          if (ttw.state == TriggerWrapper.STATE_PAUSED) {
            ttw.state=TriggerWrapper.STATE_PAUSED_BLOCKED;
          }
          timeTriggers.remove(ttw);
        }
        blockedJobs.add(job.getKey());
      }
 else       if (tw.trigger.getNextFireTime() != null) {
synchronized (lock) {
          timeTriggers.add(tw);
        }
      }
      results.add(new TriggerFiredResult(bndle));
    }
    return results;
  }
}
