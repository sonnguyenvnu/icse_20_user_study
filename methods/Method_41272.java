protected boolean removeTrigger(Connection conn,TriggerKey key) throws JobPersistenceException {
  boolean removedTrigger;
  try {
    JobDetail job=getDelegate().selectJobForTrigger(conn,getClassLoadHelper(),key,false);
    removedTrigger=deleteTriggerAndChildren(conn,key);
    if (null != job && !job.isDurable()) {
      int numTriggers=getDelegate().selectNumTriggersForJob(conn,job.getKey());
      if (numTriggers == 0) {
        deleteJobAndChildren(conn,job.getKey());
      }
    }
  }
 catch (  ClassNotFoundException e) {
    throw new JobPersistenceException("Couldn't remove trigger: " + e.getMessage(),e);
  }
catch (  SQLException e) {
    throw new JobPersistenceException("Couldn't remove trigger: " + e.getMessage(),e);
  }
  return removedTrigger;
}
