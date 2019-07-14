public boolean removeJobs(final List<JobKey> jobKeys) throws JobPersistenceException {
  return (Boolean)executeInLock(LOCK_TRIGGER_ACCESS,new TransactionCallback(){
    public Object execute(    Connection conn) throws JobPersistenceException {
      boolean allFound=true;
      for (      JobKey jobKey : jobKeys)       allFound=removeJob(conn,jobKey) && allFound;
      return allFound ? Boolean.TRUE : Boolean.FALSE;
    }
  }
);
}
