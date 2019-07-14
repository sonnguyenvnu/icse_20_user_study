private void configureSolrClientsForKerberos() throws PermanentBackendException {
  String kerberosConfig=System.getProperty("java.security.auth.login.config");
  if (kerberosConfig == null) {
    throw new PermanentBackendException("Unable to configure kerberos for solr client. System property 'java.security.auth.login.config' is not set.");
  }
  logger.debug("Using kerberos configuration file located at '{}'.",kerberosConfig);
  try (Krb5HttpClientBuilder krbBuild=new Krb5HttpClientBuilder()){
    SolrHttpClientBuilder kb=krbBuild.getBuilder();
    HttpClientUtil.setHttpClientBuilder(kb);
    HttpRequestInterceptor bufferedEntityInterceptor=new HttpRequestInterceptor(){
      @Override public void process(      HttpRequest request,      HttpContext context) throws HttpException, IOException {
        if (request instanceof HttpEntityEnclosingRequest) {
          HttpEntityEnclosingRequest enclosingRequest=((HttpEntityEnclosingRequest)request);
          HttpEntity requestEntity=enclosingRequest.getEntity();
          enclosingRequest.setEntity(new BufferedHttpEntity(requestEntity));
        }
      }
    }
;
    HttpClientUtil.addRequestInterceptor(bufferedEntityInterceptor);
    HttpRequestInterceptor preemptiveAuth=new PreemptiveAuth(new KerberosScheme());
    HttpClientUtil.addRequestInterceptor(preemptiveAuth);
  }
 }
