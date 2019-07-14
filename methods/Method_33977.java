/** 
 * Selects a proxy for the given URL.
 * @param requestTokenURL The URL
 * @return The proxy.
 */
protected Proxy selectProxy(URL requestTokenURL){
  try {
    List<Proxy> selectedProxies=getProxySelector().select(requestTokenURL.toURI());
    return selectedProxies.isEmpty() ? Proxy.NO_PROXY : selectedProxies.get(0);
  }
 catch (  URISyntaxException e) {
    throw new IllegalArgumentException(e);
  }
}
