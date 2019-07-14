private SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
  X509TrustManager trustManager=new X509TrustManager(){
    @Override public void checkClientTrusted(    X509Certificate[] chain,    String authType) throws CertificateException {
    }
    @Override public void checkServerTrusted(    X509Certificate[] chain,    String authType) throws CertificateException {
    }
    @Override public X509Certificate[] getAcceptedIssuers(){
      return null;
    }
  }
;
  SSLContext sc=SSLContext.getInstance("SSLv3");
  sc.init(null,new TrustManager[]{trustManager},null);
  return sc;
}
