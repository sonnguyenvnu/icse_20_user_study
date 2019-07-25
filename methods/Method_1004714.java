protected void dependencies(ObjectNode parent,DependenciesCapability capability){
  ObjectNode dependencies=nodeFactory.objectNode();
  dependencies.put("type",capability.getType().getName());
  ArrayNode values=nodeFactory.arrayNode();
  values.addAll(capability.getContent().stream().map(this::mapDependencyGroup).collect(Collectors.toList()));
  dependencies.set("values",values);
  parent.set(capability.getId(),dependencies);
}
