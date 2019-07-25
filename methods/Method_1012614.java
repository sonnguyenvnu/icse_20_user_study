public static synchronized void initialize(){
  final P6ModuleManager moduleManager=P6ModuleManager.getInstance();
  if (null == moduleManager) {
    return;
  }
  final P6SpyOptions opts=moduleManager.getOptions(P6SpyOptions.class);
  logger=opts.getAppenderInstance();
  if (logger != null) {
    if (logger instanceof FileLogger) {
      final String logfile=opts.getLogfile();
      ((FileLogger)logger).setLogfile(logfile);
    }
    if (logger instanceof FormattedLogger) {
      final MessageFormattingStrategy strategy=opts.getLogMessageFormatInstance();
      if (strategy != null) {
        ((FormattedLogger)logger).setStrategy(strategy);
      }
    }
  }
}
