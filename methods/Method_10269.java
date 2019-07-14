/** 
 * Sets the SSLSocketFactory to user when making requests. By default, a new, default SSLSocketFactory is used.
 * @param sslSocketFactory the socket factory to use for https requests.
 */
public void setSSLSocketFactory(SSLSocketFactory sslSocketFactory){
  this.httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https",sslSocketFactory,443));
}
