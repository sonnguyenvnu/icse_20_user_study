/** 
 * Schedules the given sets of jobs and triggers.
 * @param sched job scheduler.
 * @exception SchedulerException if the Job or Trigger cannot be added to the Scheduler, or there is an internal Scheduler error.
 */
@SuppressWarnings("ConstantConditions") protected void scheduleJobs(Scheduler sched) throws SchedulerException {
  List<JobDetail> jobs=new LinkedList<JobDetail>(getLoadedJobs());
  List<MutableTrigger> triggers=new LinkedList<MutableTrigger>(getLoadedTriggers());
  log.info("Adding " + jobs.size() + " jobs, " + triggers.size() + " triggers.");
  Map<JobKey,List<MutableTrigger>> triggersByFQJobName=buildTriggersByFQJobNameMap(triggers);
  Iterator<JobDetail> itr=jobs.iterator();
  while (itr.hasNext()) {
    JobDetail detail=itr.next();
    itr.remove();
    JobDetail dupeJ=null;
    try {
      dupeJ=sched.getJobDetail(detail.getKey());
    }
 catch (    JobPersistenceException e) {
      if (e.getCause() instanceof ClassNotFoundException && isOverWriteExistingData()) {
        log.info("Removing job: " + detail.getKey());
        sched.deleteJob(detail.getKey());
      }
 else {
        throw e;
      }
    }
    if ((dupeJ != null)) {
      if (!isOverWriteExistingData() && isIgnoreDuplicates()) {
        log.info("Not overwriting existing job: " + dupeJ.getKey());
        continue;
      }
      if (!isOverWriteExistingData() && !isIgnoreDuplicates()) {
        throw new ObjectAlreadyExistsException(detail);
      }
    }
    if (dupeJ != null) {
      log.info("Replacing job: " + detail.getKey());
    }
 else {
      log.info("Adding job: " + detail.getKey());
    }
    List<MutableTrigger> triggersOfJob=triggersByFQJobName.get(detail.getKey());
    if (!detail.isDurable() && (triggersOfJob == null || triggersOfJob.size() == 0)) {
      if (dupeJ == null) {
        throw new SchedulerException("A new job defined without any triggers must be durable: " + detail.getKey());
      }
      if ((dupeJ.isDurable() && (sched.getTriggersOfJob(detail.getKey()).size() == 0))) {
        throw new SchedulerException("Can't change existing durable job without triggers to non-durable: " + detail.getKey());
      }
    }
    if (dupeJ != null || detail.isDurable()) {
      if (triggersOfJob != null && triggersOfJob.size() > 0)       sched.addJob(detail,true,true);
 else       sched.addJob(detail,true,false);
    }
 else {
      boolean addJobWithFirstSchedule=true;
      for (      MutableTrigger trigger : triggersOfJob) {
        triggers.remove(trigger);
        if (trigger.getStartTime() == null) {
          trigger.setStartTime(new Date());
        }
        Trigger dupeT=sched.getTrigger(trigger.getKey());
        if (dupeT != null) {
          if (isOverWriteExistingData()) {
            if (log.isDebugEnabled()) {
              log.debug("Rescheduling job: " + trigger.getJobKey() + " with updated trigger: " + trigger.getKey());
            }
          }
 else           if (isIgnoreDuplicates()) {
            log.info("Not overwriting existing trigger: " + dupeT.getKey());
            continue;
          }
 else {
            throw new ObjectAlreadyExistsException(trigger);
          }
          if (!dupeT.getJobKey().equals(trigger.getJobKey())) {
            log.warn("Possibly duplicately named ({}) triggers in jobs xml file! ",trigger.getKey());
          }
          sched.rescheduleJob(trigger.getKey(),trigger);
        }
 else {
          if (log.isDebugEnabled()) {
            log.debug("Scheduling job: " + trigger.getJobKey() + " with trigger: " + trigger.getKey());
          }
          try {
            if (addJobWithFirstSchedule) {
              sched.scheduleJob(detail,trigger);
              addJobWithFirstSchedule=false;
            }
 else {
              sched.scheduleJob(trigger);
            }
          }
 catch (          ObjectAlreadyExistsException e) {
            if (log.isDebugEnabled()) {
              log.debug("Adding trigger: " + trigger.getKey() + " for job: " + detail.getKey() + " failed because the trigger already existed.  " + "This is likely due to a race condition between multiple instances " + "in the cluster.  Will try to reschedule instead.");
            }
            sched.rescheduleJob(trigger.getKey(),trigger);
          }
        }
      }
    }
  }
  for (  MutableTrigger trigger : triggers) {
    if (trigger.getStartTime() == null) {
      trigger.setStartTime(new Date());
    }
    Trigger dupeT=sched.getTrigger(trigger.getKey());
    if (dupeT != null) {
      if (isOverWriteExistingData()) {
        if (log.isDebugEnabled()) {
          log.debug("Rescheduling job: " + trigger.getJobKey() + " with updated trigger: " + trigger.getKey());
        }
      }
 else       if (isIgnoreDuplicates()) {
        log.info("Not overwriting existing trigger: " + dupeT.getKey());
        continue;
      }
 else {
        throw new ObjectAlreadyExistsException(trigger);
      }
      if (!dupeT.getJobKey().equals(trigger.getJobKey())) {
        log.warn("Possibly duplicately named ({}) triggers in jobs xml file! ",trigger.getKey());
      }
      sched.rescheduleJob(trigger.getKey(),trigger);
    }
 else {
      if (log.isDebugEnabled()) {
        log.debug("Scheduling job: " + trigger.getJobKey() + " with trigger: " + trigger.getKey());
      }
      try {
        sched.scheduleJob(trigger);
      }
 catch (      ObjectAlreadyExistsException e) {
        if (log.isDebugEnabled()) {
          log.debug("Adding trigger: " + trigger.getKey() + " for job: " + trigger.getJobKey() + " failed because the trigger already existed.  " + "This is likely due to a race condition between multiple instances " + "in the cluster.  Will try to reschedule instead.");
        }
        sched.rescheduleJob(trigger.getKey(),trigger);
      }
    }
  }
}
