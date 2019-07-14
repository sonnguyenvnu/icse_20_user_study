/** 
 * Manually override the JVM's TrustManager to accept all HTTPS connections. Use this ONLY for testing, and even at that use it cautiously. Someone could steal your API keys with a MITM attack!
 * @deprecated create an exclusion specific to your need rather than changing all behavior
 */
@Deprecated public static void trustAllCerts() throws Exception {
  TrustManager[] trustAllCerts=new TrustManager[]{new X509TrustManager(){
    @Override public java.security.cert.X509Certificate[] getAcceptedIssuers(){
      return null;
    }
    @Override public void checkClientTrusted(    X509Certificate[] certs,    String authType){
    }
    @Override public void checkServerTrusted(    X509Certificate[] certs,    String authType){
    }
  }
};
  SSLContext sc=SSLContext.getInstance("SSL");
  sc.init(null,trustAllCerts,new java.security.SecureRandom());
  HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
  HostnameVerifier allHostsValid=new HostnameVerifier(){
    @Override public boolean verify(    String hostname,    SSLSession session){
      return true;
    }
  }
;
  HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
}
