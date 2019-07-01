@Override public InputStream _XXXXX_(String filePath) throws IOException {
  try {
    DistributedLogManager dlm=namespace.openLog(filePath);
    LogReader reader;
    try {
      reader=dlm.openLogReader(DLSN.InitialDLSN);
    }
 catch (    LogNotFoundException|LogEmptyException e) {
      throw new FileNotFoundException(filePath);
    }
    return new BufferedInputStream(new DLInputStream(dlm,reader,0L),128 * 1024);
  }
 catch (  LogNotFoundException e) {
    throw new FileNotFoundException(filePath);
  }
}