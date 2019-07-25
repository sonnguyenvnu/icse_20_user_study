@Override public ResultCode log(List<LogEntry> entries){
  try {
    return client.Log(entries);
  }
 catch (  TException e) {
    LOG.log(Level.WARNING,"Failed to submit log request!.",e);
    return ResultCode.TRY_LATER;
  }
}
