/** 
 * Create dummy <code>SchedulerStateRecord</code> objects for fired triggers that have no scheduler state record.  Checkin timestamp and interval are left as zero on these dummy <code>SchedulerStateRecord</code> objects.
 * @param schedulerStateRecords List of all current <code>SchedulerStateRecords</code>
 */
private List<SchedulerStateRecord> findOrphanedFailedInstances(Connection conn,List<SchedulerStateRecord> schedulerStateRecords) throws SQLException, NoSuchDelegateException {
  List<SchedulerStateRecord> orphanedInstances=new ArrayList<SchedulerStateRecord>();
  Set<String> allFiredTriggerInstanceNames=getDelegate().selectFiredTriggerInstanceNames(conn);
  if (!allFiredTriggerInstanceNames.isEmpty()) {
    for (    SchedulerStateRecord rec : schedulerStateRecords) {
      allFiredTriggerInstanceNames.remove(rec.getSchedulerInstanceId());
    }
    for (    String inst : allFiredTriggerInstanceNames) {
      SchedulerStateRecord orphanedInstance=new SchedulerStateRecord();
      orphanedInstance.setSchedulerInstanceId(inst);
      orphanedInstances.add(orphanedInstance);
      getLog().warn("Found orphaned fired triggers for instance: " + orphanedInstance.getSchedulerInstanceId());
    }
  }
  return orphanedInstances;
}
