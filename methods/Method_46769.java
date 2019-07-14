@Override protected List<String> list(URL url,String path) throws IOException {
  String urlString=url.toString();
  String baseUrlString=urlString.endsWith("/") ? urlString : urlString.concat("/");
  Resource[] resources=resourceResolver.getResources(baseUrlString + "**/*.class");
  return Stream.of(resources).map(resource -> preserveSubpackageName(baseUrlString,resource,path)).collect(Collectors.toList());
}
