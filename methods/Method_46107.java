static synchronized boolean backup(String address,Map<String,ProviderGroup> memoryCache){
  File lockFile=new File(address + ".lock");
  while (lockFile.exists()) {
    if (LOGGER.isWarnEnabled()) {
      LOGGER.warn("Other process is writing, retry after 1s");
    }
    try {
      Thread.sleep(1000);
    }
 catch (    Exception e) {
    }
    if (lockFile.exists()) {
      if ((RpcRuntimeContext.now() - lockFile.lastModified()) > 10000) {
        boolean ret=lockFile.delete();
        if (LOGGER.isWarnEnabled()) {
          LOGGER.warn("Other process is locking over 60s, force release : {}",ret);
        }
      }
 else {
        if (LOGGER.isWarnEnabled()) {
          LOGGER.warn("Other process is stilling writing, waiting 1s ...");
        }
      }
    }
  }
  boolean created=false;
  try {
    lockFile.getParentFile().mkdirs();
    created=lockFile.createNewFile();
    if (!created) {
      if (LOGGER.isWarnEnabled()) {
        LOGGER.warn("Create lock file false, may be other process is writing. aborted");
      }
      return false;
    }
    String content=StringUtils.defaultString(marshalCache(memoryCache));
    File regFile=new File(address);
    if (regFile.exists()) {
      if (!regFile.renameTo(new File(address + ".bak"))) {
        regFile.delete();
      }
    }
    if (regFile.createNewFile()) {
      RandomAccessFile randomAccessFile=null;
      FileLock lock=null;
      try {
        randomAccessFile=new RandomAccessFile(regFile,"rw");
        FileChannel fileChannel=randomAccessFile.getChannel();
        lock=fileChannel.tryLock();
        fileChannel.write(RpcConstants.DEFAULT_CHARSET.encode(content));
        fileChannel.force(false);
      }
  finally {
        if (lock != null) {
          lock.release();
        }
        if (randomAccessFile != null) {
          randomAccessFile.close();
        }
      }
      LOGGER.info("Write backup file to {}",regFile.getAbsolutePath());
      regFile.setLastModified(RpcRuntimeContext.now());
    }
  }
 catch (  Exception e) {
    LOGGER.error("Backup registry file error !",e);
  }
 finally {
    if (created) {
      boolean deleted=lockFile.delete();
      if (!deleted) {
        if (LOGGER.isWarnEnabled()) {
          LOGGER.warn("Lock file create by this thread, but failed to delete it," + " may be the elapsed time of this backup is too long");
        }
      }
    }
  }
  return true;
}
