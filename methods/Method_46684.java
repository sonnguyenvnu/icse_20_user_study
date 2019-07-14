@Override public long machineIdSync() throws TxManagerException {
  long machineMaxSize=~(-1L << (64 - 1 - managerConfig.getSeqLen())) - 1;
  long timeout=managerConfig.getHeartTime() + 2000;
  long id;
  try {
    id=fastStorage.acquireMachineId(machineMaxSize,timeout);
  }
 catch (  FastStorageException e) {
    throw new TxManagerException(e);
  }
  log.info("Acquired machine id {}, max machine id is: {}",id,machineMaxSize);
  return id;
}
