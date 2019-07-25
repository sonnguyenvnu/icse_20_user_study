@Override public void append(String log) throws IOException {
  if (bos != null) {
    waitUntilRollFinish();
    if (shouldRollOverNow() && isRolling.compareAndSet(false,true)) {
      try {
        rollOver();
        nextFlushTime=System.currentTimeMillis() + LOG_FLUSH_INTERVAL;
      }
  finally {
        isRolling.set(false);
      }
    }
 else {
      long now;
      if ((now=System.currentTimeMillis()) >= nextFlushTime) {
        flush();
        nextFlushTime=now + LOG_FLUSH_INTERVAL;
      }
    }
    byte[] bytes=log.getBytes(TracerLogRootDaemon.DEFAULT_CHARSET);
    write(bytes);
  }
}
