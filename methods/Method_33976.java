/** 
 * Open a connection to the given URL.
 * @param requestTokenURL The request token URL.
 * @return The HTTP URL connection.
 */
protected HttpURLConnection openConnection(URL requestTokenURL){
  try {
    HttpURLConnection connection=(HttpURLConnection)requestTokenURL.openConnection(selectProxy(requestTokenURL));
    connection.setConnectTimeout(getConnectionTimeout());
    connection.setReadTimeout(getReadTimeout());
    return connection;
  }
 catch (  IOException e) {
    throw new OAuthRequestFailedException("Failed to open an OAuth connection.",e);
  }
}
