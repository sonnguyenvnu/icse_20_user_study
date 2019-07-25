@Override public LoggerBackend create(String loggingClassName){
  return new Slf4jLoggerBackend(LoggerFactory.getLogger(loggingClassName.replace('$','.')));
}
