private ActualHttpServer newBaseServer(final int port,final HttpsCertificate certificate){
  if (certificate != null) {
    return createHttpsLogServer(port,certificate);
  }
  return createLogServer(port);
}
