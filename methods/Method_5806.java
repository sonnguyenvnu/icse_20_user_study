/** 
 * Establishes a connection, following redirects to do so where permitted.
 */
private HttpURLConnection makeConnection(DataSpec dataSpec) throws IOException {
  URL url=new URL(dataSpec.uri.toString());
  @HttpMethod int httpMethod=dataSpec.httpMethod;
  byte[] httpBody=dataSpec.httpBody;
  long position=dataSpec.position;
  long length=dataSpec.length;
  boolean allowGzip=dataSpec.isFlagSet(DataSpec.FLAG_ALLOW_GZIP);
  boolean allowIcyMetadata=dataSpec.isFlagSet(DataSpec.FLAG_ALLOW_ICY_METADATA);
  if (!allowCrossProtocolRedirects) {
    return makeConnection(url,httpMethod,httpBody,position,length,allowGzip,allowIcyMetadata,true);
  }
  int redirectCount=0;
  while (redirectCount++ <= MAX_REDIRECTS) {
    HttpURLConnection connection=makeConnection(url,httpMethod,httpBody,position,length,allowGzip,allowIcyMetadata,false);
    int responseCode=connection.getResponseCode();
    String location=connection.getHeaderField("Location");
    if ((httpMethod == DataSpec.HTTP_METHOD_GET || httpMethod == DataSpec.HTTP_METHOD_HEAD) && (responseCode == HttpURLConnection.HTTP_MULT_CHOICE || responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_SEE_OTHER || responseCode == HTTP_STATUS_TEMPORARY_REDIRECT || responseCode == HTTP_STATUS_PERMANENT_REDIRECT)) {
      connection.disconnect();
      url=handleRedirect(url,location);
    }
 else     if (httpMethod == DataSpec.HTTP_METHOD_POST && (responseCode == HttpURLConnection.HTTP_MULT_CHOICE || responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_SEE_OTHER)) {
      connection.disconnect();
      httpMethod=DataSpec.HTTP_METHOD_GET;
      httpBody=null;
      url=handleRedirect(url,location);
    }
 else {
      return connection;
    }
  }
  throw new NoRouteToHostException("Too many redirects: " + redirectCount);
}
