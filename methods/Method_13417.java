public List<URL> findSubscribedDubboMetadataServiceURLs(String serviceName,String group,String version,String protocol){
  String serviceKey=URL.buildKey(serviceName,group,version);
  List<URL> urls=subscribedDubboMetadataServiceURLs.get(serviceKey);
  if (isEmpty(urls)) {
    return emptyList();
  }
  return hasText(protocol) ? urls.stream().filter(url -> url.getProtocol().equalsIgnoreCase(protocol)).collect(Collectors.toList()) : unmodifiableList(urls);
}
