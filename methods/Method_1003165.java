@Override public void close(Session session){
  if (trace.isDebugEnabled()) {
    trace.debug("close");
  }
  try {
    writeRowCount();
  }
  finally {
    store.incrementChangeCount();
  }
}
