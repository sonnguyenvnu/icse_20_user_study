private static void configure(Configuration configuration){
  Logger rootLogger=Logger.getLogger(configuration.rootLoggerName);
  if (configuration.logToStderr) {
    setLoggerToStderr(rootLogger);
  }
 else   if (configuration.alsoLogToStderr) {
    setLoggerToAlsoStderr(rootLogger);
  }
  if (configuration.useGLogFormatter) {
    setGLogFormatter(rootLogger);
  }
  if (configuration.vlog != null) {
    setVlog(rootLogger,configuration.vlog);
  }
  if (configuration.vmodule != null) {
    setVmodules(configuration.vmodule);
  }
}
