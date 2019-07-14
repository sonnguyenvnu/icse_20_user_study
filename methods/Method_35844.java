public String getBaseUrl(){
  String portPart=isStandardPort(scheme,port) ? "" : ":" + port;
  return scheme + "://" + host + portPart;
}
