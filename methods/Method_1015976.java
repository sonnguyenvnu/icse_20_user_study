@Override public StorageFactory<byte[]> store(String path,byte[] asset) throws IOException {
  if (this.ftp != null && this.ftp_fail.get() < 10) {
    retryloop:     for (int retry=0; retry < 40; retry++) {
      try {
        StorageFactory<byte[]> sf=this.ftp.getStorage().store(path,asset);
        this.ftp_fail.set(0);
        return sf;
      }
 catch (      IOException e) {
        String cause=e.getMessage();
        if (cause != null && cause.contains("refused"))         break retryloop;
        if (cause.indexOf("421") >= 0) {
          try {
            Thread.sleep(retry * 500);
          }
 catch (          InterruptedException e1) {
          }
          continue retryloop;
        }
        Data.logger.debug("GridStorage.store trying to connect to the ftp server failed, attempt " + retry + ": " + cause,e);
      }
    }
    this.ftp_fail.incrementAndGet();
  }
  if (this.mcp != null)   try {
    return this.mcp.getStorage().store(path,asset);
  }
 catch (  IOException e) {
    Data.logger.debug("GridStorage.store trying to connect to the mcp failed: " + e.getMessage(),e);
  }
  return super.store(path,asset);
}
