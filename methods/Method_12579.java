private List<Resource> resolveAssets(String location) throws IOException {
  String widerLocation=toPattern(location);
  return Stream.concat(Arrays.stream(this.resolver.getResources(widerLocation + "**/*.js")),Arrays.stream(this.resolver.getResources(widerLocation + "**/*.css"))).collect(Collectors.toList());
}
