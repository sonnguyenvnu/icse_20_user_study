/** 
 * Creates a custom  {@link SSLSocketFactory} that accepts an expired certificate.
 * @param subjectPrincipalName RFC 2253 name on the expired certificate
 * @return An {@link SSLSocketFactory} that will accept the passed certificate if it is expired
 */
public static SSLSocketFactory createExpiredAcceptingSSLSocketFactory(final String subjectPrincipalName){
  try {
    final TrustManagerFactory trustManagerFactory=TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    trustManagerFactory.init((KeyStore)null);
    X509TrustManager trustManager=new X509TrustManager(){
      private X509TrustManager getDefaultTrustManager(){
        TrustManager trustManagers[]=trustManagerFactory.getTrustManagers();
        for (        TrustManager trustManager : trustManagers) {
          if (trustManager instanceof X509TrustManager)           return (X509TrustManager)trustManager;
        }
        throw new IllegalStateException();
      }
      private boolean certificateMatches(      X509Certificate[] certs,      boolean needsToBeExpired){
        for (        X509Certificate cert : certs)         if (cert.getSubjectX500Principal().getName().equals(subjectPrincipalName) && (!needsToBeExpired || cert.getNotAfter().before(new Date())))         return true;
        return false;
      }
      @Override public X509Certificate[] getAcceptedIssuers(){
        return getDefaultTrustManager().getAcceptedIssuers();
      }
      @Override public void checkClientTrusted(      X509Certificate[] certs,      String authType) throws CertificateException {
        System.out.println("checking client trusted: " + Arrays.toString(certs));
        getDefaultTrustManager().checkClientTrusted(certs,authType);
      }
      @Override public void checkServerTrusted(      X509Certificate[] certs,      String authType) throws CertificateException {
        X509Certificate matchingCertificate;
        try {
          getDefaultTrustManager().checkServerTrusted(certs,authType);
          if (certificateMatches(certs,false))           throw new CertificateException("Update code to reject expired certificate, up-to-date certificate found: " + subjectPrincipalName);
        }
 catch (        CertificateException e) {
          for (Throwable t=e; t != null; t=t.getCause())           if (t instanceof CertificateExpiredException && certificateMatches(certs,true))           return;
          throw e;
        }
      }
    }
;
    SSLContext sslContext=SSLContext.getInstance("TLS");
    sslContext.init(null,new TrustManager[]{trustManager},null);
    return sslContext.getSocketFactory();
  }
 catch (  GeneralSecurityException e) {
    throw new IllegalStateException(e);
  }
}
