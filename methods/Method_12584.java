@ModelAttribute(value="baseUrl",binding=false) public String getBaseUrl(UriComponentsBuilder uriBuilder){
  UriComponents publicComponents=UriComponentsBuilder.fromUriString(this.publicUrl).build();
  if (publicComponents.getScheme() != null) {
    uriBuilder.scheme(publicComponents.getScheme());
  }
  if (publicComponents.getHost() != null) {
    uriBuilder.host(publicComponents.getHost());
  }
  if (publicComponents.getPort() != -1) {
    uriBuilder.port(publicComponents.getPort());
  }
  if (publicComponents.getPath() != null) {
    uriBuilder.path(publicComponents.getPath());
  }
  return uriBuilder.path("/").toUriString();
}
