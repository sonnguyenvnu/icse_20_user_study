@Override public void _XXXXX_() throws ReplicationException.UnavailableException {
  if (LOG.isDebugEnabled()) {
    LOG.debug("enableLedegerReplication()");
  }
  try {
    zkc.delete(basePath + '/' + BookKeeperConstants.DISABLE_NODE,-1);
    LOG.info("Resuming automatic ledger re-replication");
  }
 catch (  KeeperException.NoNodeException ke) {
    LOG.warn("AutoRecovery is already enabled!",ke);
    throw new ReplicationException.UnavailableException("AutoRecovery is already enabled!",ke);
  }
catch (  KeeperException ke) {
    LOG.error("Exception while resuming ledger replication",ke);
    throw new ReplicationException.UnavailableException("Exception while resuming auto ledger re-replication",ke);
  }
catch (  InterruptedException ie) {
    Thread.currentThread().interrupt();
    throw new ReplicationException.UnavailableException("Interrupted while resuming auto ledger re-replication",ie);
  }
}