@Override protected String getServiceUrl(){
  if (instance.getServiceUrl() != null) {
    return instance.getServiceUrl();
  }
  return UriComponentsBuilder.fromUriString(getServiceBaseUrl()).path("/").path(getServerContextPath()).toUriString();
}
