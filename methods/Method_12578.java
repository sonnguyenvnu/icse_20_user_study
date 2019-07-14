public List<UiExtension> scan(String... locations) throws IOException {
  List<UiExtension> extensions=new ArrayList<>();
  for (  String location : locations) {
    for (    Resource resource : resolveAssets(location)) {
      String resourcePath=this.getResourcePath(location,resource);
      if (resourcePath != null && resource.isReadable()) {
        UiExtension extension=new UiExtension(resourcePath,location + resourcePath);
        log.debug("Found UiExtension {}",extension);
        extensions.add(extension);
      }
    }
  }
  return extensions;
}
