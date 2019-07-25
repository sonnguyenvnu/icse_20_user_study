private void init(String uri,int connectTimeout,int readTimeout,boolean exeptAllCerts){
  logger.debug("init HttpTransportImpl");
  this.uri=fixURI(uri);
  this.connectTimeout=connectTimeout;
  this.readTimeout=readTimeout;
  if (exeptAllCerts) {
    sslSocketFactory=generateSSLContextWhichAcceptAllSSLCertificats();
  }
 else {
    if (config != null) {
      cert=config.getCert();
      logger.debug("generate SSLcontext from config cert");
      if (StringUtils.isNotBlank(cert)) {
        sslSocketFactory=generateSSLContextFromPEMCertString(cert);
      }
 else {
        if (StringUtils.isNotBlank(config.getTrustCertPath())) {
          logger.debug("generate SSLcontext from config cert path");
          cert=readPEMCertificateStringFromFile(config.getTrustCertPath());
          if (StringUtils.isNotBlank(cert)) {
            sslSocketFactory=generateSSLContextFromPEMCertString(cert);
          }
        }
 else {
          logger.debug("generate SSLcontext from server");
          cert=getPEMCertificateFromServer(this.uri);
          sslSocketFactory=generateSSLContextFromPEMCertString(cert);
          if (sslSocketFactory != null) {
            config.setCert(cert);
          }
        }
      }
    }
 else {
      logger.debug("generate SSLcontext from server");
      cert=getPEMCertificateFromServer(this.uri);
      sslSocketFactory=generateSSLContextFromPEMCertString(cert);
    }
  }
}
