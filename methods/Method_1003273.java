private void reopen(int i,IOException e) throws IOException {
  if (i > 20) {
    throw e;
  }
  if (!(e instanceof ClosedByInterruptException) && !(e instanceof ClosedChannelException)) {
    throw e;
  }
  Thread.interrupted();
  FileChannel before=channel;
synchronized (this) {
    if (before == channel) {
      open();
      reLock();
    }
  }
}
