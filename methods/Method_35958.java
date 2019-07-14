public static Socket getTlsSocket(Response response){
  HttpChannel httpChannel=response.getHttpOutput().getHttpChannel();
  SslConnection.DecryptedEndPoint sslEndpoint=(SslConnection.DecryptedEndPoint)httpChannel.getEndPoint();
  Object endpoint=sslEndpoint.getSslConnection().getEndPoint();
  try {
    return (Socket)endpoint.getClass().getMethod("getSocket").invoke(endpoint);
  }
 catch (  Exception e) {
    return throwUnchecked(e,Socket.class);
  }
}
