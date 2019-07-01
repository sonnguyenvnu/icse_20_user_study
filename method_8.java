@Override public long _XXXXX_(String filePath) throws IOException {
  try (DistributedLogManager dlm=namespace.openLog(filePath)){
    return dlm.getLastTxId();
  }
 catch (  LogNotFoundException e) {
    throw new FileNotFoundException(filePath);
  }
catch (  LogEmptyException e) {
    return 0;
  }
}