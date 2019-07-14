/** 
 * Normalize the URL for use in the signature. The OAuth spec says the URL protocol and host are to be lower-case, and the query and fragments are to be stripped.
 * @param url The URL.
 * @return The URL normalized for use in the signature.
 */
protected String normalizeUrl(String url){
  try {
    URL requestURL=new URL(url);
    StringBuilder normalized=new StringBuilder(requestURL.getProtocol().toLowerCase()).append("://").append(requestURL.getHost().toLowerCase());
    if ((requestURL.getPort() >= 0) && (requestURL.getPort() != requestURL.getDefaultPort())) {
      normalized.append(":").append(requestURL.getPort());
    }
    normalized.append(requestURL.getPath());
    return normalized.toString();
  }
 catch (  MalformedURLException e) {
    throw new IllegalStateException("Illegal URL for calculating the OAuth signature.",e);
  }
}
