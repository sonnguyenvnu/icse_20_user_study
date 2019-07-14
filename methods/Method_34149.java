private String extractPath(FrameworkEndpointHandlerMapping mapping,String page){
  String path=mapping.getPath(page);
  if (path.contains(":")) {
    return path;
  }
  return "forward:" + path;
}
