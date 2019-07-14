/** 
 * Configures a connection and opens it.
 * @param url The url to connect to.
 * @param httpMethod The http method.
 * @param httpBody The body data.
 * @param position The byte offset of the requested data.
 * @param length The length of the requested data, or {@link C#LENGTH_UNSET}.
 * @param allowGzip Whether to allow the use of gzip.
 * @param allowIcyMetadata Whether to allow ICY metadata.
 * @param followRedirects Whether to follow redirects.
 */
private HttpURLConnection makeConnection(URL url,@HttpMethod int httpMethod,byte[] httpBody,long position,long length,boolean allowGzip,boolean allowIcyMetadata,boolean followRedirects) throws IOException {
  HttpURLConnection connection=(HttpURLConnection)url.openConnection();
  connection.setConnectTimeout(connectTimeoutMillis);
  connection.setReadTimeout(readTimeoutMillis);
  if (defaultRequestProperties != null) {
    for (    Map.Entry<String,String> property : defaultRequestProperties.getSnapshot().entrySet()) {
      connection.setRequestProperty(property.getKey(),property.getValue());
    }
  }
  for (  Map.Entry<String,String> property : requestProperties.getSnapshot().entrySet()) {
    connection.setRequestProperty(property.getKey(),property.getValue());
  }
  if (!(position == 0 && length == C.LENGTH_UNSET)) {
    String rangeRequest="bytes=" + position + "-";
    if (length != C.LENGTH_UNSET) {
      rangeRequest+=(position + length - 1);
    }
    connection.setRequestProperty("Range",rangeRequest);
  }
  connection.setRequestProperty("User-Agent",userAgent);
  if (!allowGzip) {
    connection.setRequestProperty("Accept-Encoding","identity");
  }
  if (allowIcyMetadata) {
    connection.setRequestProperty(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_NAME,IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
  }
  connection.setInstanceFollowRedirects(followRedirects);
  connection.setDoOutput(httpBody != null);
  connection.setRequestMethod(DataSpec.getStringForHttpMethod(httpMethod));
  if (httpBody != null) {
    connection.setFixedLengthStreamingMode(httpBody.length);
    connection.connect();
    OutputStream os=connection.getOutputStream();
    os.write(httpBody);
    os.close();
  }
 else {
    connection.connect();
  }
  return connection;
}
