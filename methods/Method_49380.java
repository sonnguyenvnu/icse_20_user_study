public static void rollbackQuietly(JanusGraphTransaction tx){
  if (null == tx)   return;
  try {
    tx.rollback();
  }
 catch (  Throwable t) {
    log.warn("Unable to rollback transaction",t);
  }
}
