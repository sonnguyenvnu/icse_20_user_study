public String baseUrl(){
  boolean https=options.httpsSettings().enabled();
  String protocol=https ? "https" : "http";
  int port=https ? httpsPort() : port();
  return String.format("%s://localhost:%d",protocol,port);
}
