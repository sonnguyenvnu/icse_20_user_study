/** 
 * Creates new connection from current  {@link jodd.http.HttpRequest request}.
 * @see #createSocket(String,int,int)
 */
@Override public HttpConnection createHttpConnection(final HttpRequest httpRequest) throws IOException {
  final SocketHttpConnection httpConnection;
  final boolean https=httpRequest.protocol().equalsIgnoreCase("https");
  if (https) {
    SSLSocket sslSocket=createSSLSocket(httpRequest.host(),httpRequest.port(),httpRequest.connectionTimeout(),httpRequest.trustAllCertificates(),httpRequest.verifyHttpsHost());
    httpConnection=new SocketHttpSecureConnection(sslSocket);
  }
 else {
    Socket socket=createSocket(httpRequest.host(),httpRequest.port(),httpRequest.connectionTimeout());
    httpConnection=new SocketHttpConnection(socket);
  }
  httpConnection.setTimeout(httpRequest.timeout());
  try {
    httpConnection.init();
  }
 catch (  Throwable throwable) {
    httpConnection.close();
    throw new HttpException(throwable);
  }
  return httpConnection;
}
