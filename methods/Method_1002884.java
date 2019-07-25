VirtualHost decorate(@Nullable Function<Service<HttpRequest,HttpResponse>,Service<HttpRequest,HttpResponse>> decorator){
  if (decorator == null) {
    return this;
  }
  final List<ServiceConfig> services=this.services.stream().map(cfg -> cfg.withDecoratedService(decorator)).collect(Collectors.toList());
  return new VirtualHost(defaultHostname(),hostnamePattern(),sslContext(),services,RejectedRouteHandler.DISABLED,host -> accessLogger,requestTimeoutMillis(),maxRequestLength(),verboseResponses(),requestContentPreviewerFactory(),responseContentPreviewerFactory(),accessLogWriter(),shutdownAccessLogWriterOnStop());
}
