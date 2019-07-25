public synchronized Compute client(){
  if (refreshInterval != null && refreshInterval.millis() != 0) {
    if (client != null && (refreshInterval.millis() < 0 || (System.currentTimeMillis() - lastRefresh) < refreshInterval.millis())) {
      if (logger.isTraceEnabled())       logger.trace("using cache to retrieve client");
      return client;
    }
    lastRefresh=System.currentTimeMillis();
  }
  try {
    gceJsonFactory=new JacksonFactory();
    logger.info("starting GCE discovery service");
    String tokenServerEncodedUrl=GceMetadataService.GCE_HOST.get(settings) + "/computeMetadata/v1/instance/service-accounts/default/token";
    ComputeCredential credential=new ComputeCredential.Builder(getGceHttpTransport(),gceJsonFactory).setTokenServerEncodedUrl(tokenServerEncodedUrl).build();
    Access.doPrivilegedIOException(credential::refreshToken);
    logger.debug("token [{}] will expire in [{}] s",credential.getAccessToken(),credential.getExpiresInSeconds());
    if (credential.getExpiresInSeconds() != null) {
      refreshInterval=TimeValue.timeValueSeconds(credential.getExpiresInSeconds() - 1);
    }
    Compute.Builder builder=new Compute.Builder(getGceHttpTransport(),gceJsonFactory,null).setApplicationName(VERSION).setRootUrl(GCE_ROOT_URL.get(settings));
    if (RETRY_SETTING.exists(settings)) {
      TimeValue maxWait=MAX_WAIT_SETTING.get(settings);
      RetryHttpInitializerWrapper retryHttpInitializerWrapper;
      if (maxWait.getMillis() > 0) {
        retryHttpInitializerWrapper=new RetryHttpInitializerWrapper(credential,maxWait);
      }
 else {
        retryHttpInitializerWrapper=new RetryHttpInitializerWrapper(credential);
      }
      builder.setHttpRequestInitializer(retryHttpInitializerWrapper);
    }
 else {
      builder.setHttpRequestInitializer(credential);
    }
    this.client=builder.build();
  }
 catch (  Exception e) {
    logger.warn("unable to start GCE discovery service",e);
    throw new IllegalArgumentException("unable to start GCE discovery service",e);
  }
  return this.client;
}
