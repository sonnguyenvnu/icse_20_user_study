public void workerIterationEnd(ScanMetrics metrics){
  try {
    if (null != managementSystem && managementSystem.isOpen())     managementSystem.commit();
    if (writeTx != null && writeTx.isOpen())     writeTx.commit();
    metrics.incrementCustom(SUCCESS_TX);
  }
 catch (  RuntimeException e) {
    log.error("Transaction commit threw runtime exception:",e);
    metrics.incrementCustom(FAILED_TX);
    throw e;
  }
}
