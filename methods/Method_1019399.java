private String[] resolve(final String name,final String type){
  String response;
  try {
    response=performJsonDnsRequest(name,type);
  }
 catch (  IOException e) {
    tlsHostname=FALLBACK_TLS_HOSTNAME;
    try {
      response=performJsonDnsRequest(name,type);
    }
 catch (    IOException fallbackE) {
      return null;
    }
  }
  if (response == null) {
    return null;
  }
  return parseJsonDnsResponse(response);
}
