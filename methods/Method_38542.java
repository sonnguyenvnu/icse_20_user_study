/** 
 * Returns default SSL socket factory allowing setting trust managers.
 */
protected SSLSocketFactory getDefaultSSLSocketFactory(final boolean trustAllCertificates) throws IOException {
  if (trustAllCertificates) {
    try {
      SSLContext sc=SSLContext.getInstance(sslProtocol);
      sc.init(null,TrustManagers.TRUST_ALL_CERTS,new java.security.SecureRandom());
      return sc.getSocketFactory();
    }
 catch (    NoSuchAlgorithmException|KeyManagementException e) {
      throw new IOException(e);
    }
  }
 else {
    return (SSLSocketFactory)SSLSocketFactory.getDefault();
  }
}
