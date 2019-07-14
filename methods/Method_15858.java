@Override public boolean validateObject(PooledObject<FTPClient> p){
  try {
    p.getObject().sendNoOp();
  }
 catch (  IOException e) {
    logger.warn("validateObject ftp error!",e);
    return false;
  }
  return p.getObject().isConnected() && p.getObject().isAvailable();
}
