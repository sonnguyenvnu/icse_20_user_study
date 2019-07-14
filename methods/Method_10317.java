/** 
 * Makes HttpsURLConnection trusts a set of certificates specified by the KeyStore
 */
public void fixHttpsURLConnection(){
  HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
}
