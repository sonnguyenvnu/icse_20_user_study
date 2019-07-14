/** 
 * Get a list of all scheduler instances in the cluster that may have failed. This includes this scheduler if it is checking in for the first time.
 */
protected List<SchedulerStateRecord> findFailedInstances(Connection conn) throws JobPersistenceException {
  try {
    List<SchedulerStateRecord> failedInstances=new LinkedList<SchedulerStateRecord>();
    boolean foundThisScheduler=false;
    long timeNow=System.currentTimeMillis();
    List<SchedulerStateRecord> states=getDelegate().selectSchedulerStateRecords(conn,null);
    for (    SchedulerStateRecord rec : states) {
      if (rec.getSchedulerInstanceId().equals(getInstanceId())) {
        foundThisScheduler=true;
        if (firstCheckIn) {
          failedInstances.add(rec);
        }
      }
 else {
        if (calcFailedIfAfter(rec) < timeNow) {
          failedInstances.add(rec);
        }
      }
    }
    if (firstCheckIn) {
      failedInstances.addAll(findOrphanedFailedInstances(conn,states));
    }
    if ((!foundThisScheduler) && (!firstCheckIn)) {
      getLog().warn("This scheduler instance (" + getInstanceId() + ") is still " + "active but was recovered by another instance in the cluster.  " + "This may cause inconsistent behavior.");
    }
    return failedInstances;
  }
 catch (  Exception e) {
    lastCheckin=System.currentTimeMillis();
    throw new JobPersistenceException("Failure identifying failed instances when checking-in: " + e.getMessage(),e);
  }
}
