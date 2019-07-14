private SSLConnectionSocketFactory buildSSLConnectionSocketFactory(){
  try {
    return new SSLConnectionSocketFactory(createIgnoreVerifySSL(),new String[]{"SSLv3","TLSv1","TLSv1.1","TLSv1.2"},null,new DefaultHostnameVerifier());
  }
 catch (  KeyManagementException e) {
    logger.error("ssl connection fail",e);
  }
catch (  NoSuchAlgorithmException e) {
    logger.error("ssl connection fail",e);
  }
  return SSLConnectionSocketFactory.getSocketFactory();
}
