@Override public LoggerBackend create(String loggingClass){
  Logger logger=Logger.getLogger(loggingClass.replace('$','.'));
  return new SimpleLoggerBackend(logger);
}
