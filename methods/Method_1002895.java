/** 
 * Returns a newly-created  {@link VirtualHost} based on the properties of this builder and the servicesadded to this builder.
 */
VirtualHost build(){
  if (defaultHostname == null) {
    if ("*".equals(hostnamePattern)) {
      defaultHostname=SystemInfo.hostname();
    }
 else     if (hostnamePattern.startsWith("*.")) {
      defaultHostname=hostnamePattern.substring(2);
    }
 else {
      defaultHostname=hostnamePattern;
    }
  }
 else {
    ensureHostnamePatternMatchesDefaultHostname(hostnamePattern,defaultHostname);
  }
  final long requestTimeout=requestTimeoutMillis != null ? requestTimeoutMillis : serverBuilder.requestTimeoutMillis();
  final long maxRequest=maxRequestLength != null ? maxRequestLength : serverBuilder.maxRequestLength();
  final boolean verboseResponses=this.verboseResponses != null ? this.verboseResponses : serverBuilder.verboseResponses();
  final ContentPreviewerFactory requestContentPreviewerFactory=this.requestContentPreviewerFactory != null ? this.requestContentPreviewerFactory : serverBuilder.requestContentPreviewerFactory();
  final ContentPreviewerFactory responseContentPreviewerFactory=this.responseContentPreviewerFactory != null ? this.responseContentPreviewerFactory : serverBuilder.responseContentPreviewerFactory();
  final RejectedRouteHandler rejectedRouteHandler=this.rejectedRouteHandler != null ? this.rejectedRouteHandler : serverBuilder.rejectedRouteHandler();
  final AccessLogWriter accessLogWriter;
  final boolean shutdownAccessLogWriterOnStop;
  if (this.accessLogWriter != null) {
    accessLogWriter=this.accessLogWriter;
    shutdownAccessLogWriterOnStop=this.shutdownAccessLogWriterOnStop;
  }
 else {
    accessLogWriter=serverBuilder.accessLogWriter();
    shutdownAccessLogWriterOnStop=serverBuilder.shutdownAccessLogWriterOnStop();
  }
  final List<ServiceConfig> serviceConfigs=serviceConfigBuilders.stream().map(cfgBuilder -> {
    if (cfgBuilder.requestTimeoutMillis() == null) {
      cfgBuilder.requestTimeoutMillis(requestTimeout);
    }
    if (cfgBuilder.maxRequestLength() == null) {
      cfgBuilder.maxRequestLength(maxRequest);
    }
    if (cfgBuilder.verboseResponses() == null) {
      cfgBuilder.verboseResponses(verboseResponses);
    }
    if (cfgBuilder.requestContentPreviewerFactory() == null) {
      cfgBuilder.requestContentPreviewerFactory(requestContentPreviewerFactory);
    }
    if (cfgBuilder.responseContentPreviewerFactory() == null) {
      cfgBuilder.responseContentPreviewerFactory(responseContentPreviewerFactory);
    }
    if (cfgBuilder.accessLogWriter() == null) {
      cfgBuilder.accessLogWriter(accessLogWriter,shutdownAccessLogWriterOnStop);
    }
    return cfgBuilder.build();
  }
).collect(toImmutableList());
  if (sslContext == null && tlsSelfSigned) {
    try {
      final SelfSignedCertificate ssc=new SelfSignedCertificate(defaultHostname);
      tls(ssc.certificate(),ssc.privateKey());
    }
 catch (    Exception e) {
      throw new RuntimeException("failed to create a self signed certificate",e);
    }
  }
  final Function<VirtualHost,Logger> accessLoggerMapper=this.accessLoggerMapper != null ? this.accessLoggerMapper : serverBuilder.accessLoggerMapper();
  final VirtualHost virtualHost=new VirtualHost(defaultHostname,hostnamePattern,sslContext,serviceConfigs,rejectedRouteHandler,accessLoggerMapper,requestTimeout,maxRequest,verboseResponses,requestContentPreviewerFactory,responseContentPreviewerFactory,accessLogWriter,shutdownAccessLogWriterOnStop);
  return decorator != null ? virtualHost.decorate(decorator) : virtualHost;
}
