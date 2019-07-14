public List<String> scan(String... locations) throws IOException {
  List<String> routes=new ArrayList<>();
  for (  String location : locations) {
    for (    Resource resource : this.resolver.getResources(toPattern(location) + "**/routes.txt")) {
      if (resource.isReadable()) {
        routes.addAll(readLines(resource.getInputStream()));
      }
    }
  }
  return routes;
}
