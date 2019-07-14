private boolean releaseIfScheduleChangedSignificantly(List<OperableTrigger> triggers,long triggerTime){
  if (isCandidateNewTimeEarlierWithinReason(triggerTime,true)) {
    for (    OperableTrigger trigger : triggers) {
      qsRsrcs.getJobStore().releaseAcquiredTrigger(trigger);
    }
    triggers.clear();
    return true;
  }
  return false;
}
