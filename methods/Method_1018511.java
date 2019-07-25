@Override public void close() throws Exception {
  ensureNotClosed();
  this.isClosed=true;
}
