@Override public void destroyObject(String key,CTConnection c) throws Exception {
  TTransport t=c.getTransport();
  if (t.isOpen()) {
    t.close();
    log.trace("Closed transport {}",t);
  }
 else {
    log.trace("Not closing transport {} (already closed)",t);
  }
}
