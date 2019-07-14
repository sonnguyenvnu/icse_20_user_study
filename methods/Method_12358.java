@Override protected String getServiceBaseUrl(){
  if (cfApplicationProperties.getUris().isEmpty()) {
    return super.getServiceBaseUrl();
  }
  String uri=cfApplicationProperties.getUris().get(0);
  return "http://" + uri;
}
