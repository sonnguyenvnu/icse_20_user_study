ServiceConfig build(){
  assert requestTimeoutMillis != null;
  assert maxRequestLength != null;
  assert verboseResponses != null;
  assert requestContentPreviewerFactory != null;
  assert responseContentPreviewerFactory != null;
  assert accessLogWriter != null;
  return new ServiceConfig(route,service,loggerName,requestTimeoutMillis,maxRequestLength,verboseResponses,requestContentPreviewerFactory,responseContentPreviewerFactory,accessLogWriter,shutdownAccessLogWriterOnStop);
}
