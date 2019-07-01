@Override public Boolean _XXXXX_(JavascriptLog javascriptLog){
  Logger toUse=javascriptLog.getLoggerName() == null ? logger : LoggerFactory.getLogger(javascriptLog.getLoggerName());
  if (javascriptLog.getMessage() == null) {
    return Boolean.TRUE;
  }
  toUse.error(javascriptLog.getMessage());
  return Boolean.TRUE;
}