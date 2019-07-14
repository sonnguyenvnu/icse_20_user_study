public AopAccessLoggerSupport addParser(AccessLoggerParser parser){
  if (!loggerParsers.contains(parser)) {
    loggerParsers.add(parser);
  }
  return this;
}
