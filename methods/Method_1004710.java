@Override public String write(DependencyMetadata metadata){
  ObjectNode json=nodeFactory.objectNode();
  json.put("bootVersion",metadata.getBootVersion().toString());
  json.set("dependencies",mapNode(metadata.getDependencies().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,(entry) -> mapDependency(entry.getValue())))));
  json.set("repositories",mapNode(metadata.getRepositories().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,(entry) -> mapRepository(entry.getValue())))));
  json.set("boms",mapNode(metadata.getBoms().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,(entry) -> mapBom(entry.getValue())))));
  return json.toString();
}
