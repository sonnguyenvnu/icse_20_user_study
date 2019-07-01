public static URL _XXXXX_(String urlString) throws MalformedURLException, NoSuchAlgorithmException, KeyManagementException {
  final TrustManager[] trustAllCerts=new TrustManager[]{new TrustAllX509TrustManager()};
  final SSLContext sc=SSLContext.getInstance("SSL");
  sc.init(null,trustAllCerts,new java.security.SecureRandom());
  HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
  final HostnameVerifier allHostsValid=new HostnameVerifier(){
    public boolean verify(    String hostname,    SSLSession session){
      return true;
    }
  }
;
  HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
  return new URL(urlString);
}