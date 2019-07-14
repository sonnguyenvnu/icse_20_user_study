protected boolean doCheckin() throws JobPersistenceException {
  boolean transOwner=false;
  boolean transStateOwner=false;
  boolean recovered=false;
  Connection conn=getNonManagedTXConnection();
  try {
    List<SchedulerStateRecord> failedRecords=null;
    if (!firstCheckIn) {
      failedRecords=clusterCheckIn(conn);
      commitConnection(conn);
    }
    if (firstCheckIn || (failedRecords.size() > 0)) {
      getLockHandler().obtainLock(conn,LOCK_STATE_ACCESS);
      transStateOwner=true;
      failedRecords=(firstCheckIn) ? clusterCheckIn(conn) : findFailedInstances(conn);
      if (failedRecords.size() > 0) {
        getLockHandler().obtainLock(conn,LOCK_TRIGGER_ACCESS);
        transOwner=true;
        clusterRecover(conn,failedRecords);
        recovered=true;
      }
    }
    commitConnection(conn);
  }
 catch (  JobPersistenceException e) {
    rollbackConnection(conn);
    throw e;
  }
 finally {
    try {
      releaseLock(LOCK_TRIGGER_ACCESS,transOwner);
    }
  finally {
      try {
        releaseLock(LOCK_STATE_ACCESS,transStateOwner);
      }
  finally {
        cleanupConnection(conn);
      }
    }
  }
  firstCheckIn=false;
  return recovered;
}
