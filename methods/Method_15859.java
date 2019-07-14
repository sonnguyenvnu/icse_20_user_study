@Override public void activateObject(PooledObject<FTPClient> p) throws Exception {
  p.getObject().sendNoOp();
}
