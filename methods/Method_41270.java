public void storeJobsAndTriggers(final Map<JobDetail,Set<? extends Trigger>> triggersAndJobs,final boolean replace) throws JobPersistenceException {
  executeInLock((isLockOnInsert() || replace) ? LOCK_TRIGGER_ACCESS : null,new VoidTransactionCallback(){
    public void executeVoid(    Connection conn) throws JobPersistenceException {
      for (      JobDetail job : triggersAndJobs.keySet()) {
        storeJob(conn,job,replace);
        for (        Trigger trigger : triggersAndJobs.get(job)) {
          storeTrigger(conn,(OperableTrigger)trigger,job,replace,Constants.STATE_WAITING,false,false);
        }
      }
    }
  }
);
}
