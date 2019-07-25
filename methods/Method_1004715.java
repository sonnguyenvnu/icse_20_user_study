protected void type(ObjectNode parent,TypeCapability capability){
  ObjectNode type=nodeFactory.objectNode();
  type.put("type","action");
  Type defaultType=capability.getDefault();
  if (defaultType != null) {
    type.put("default",defaultType.getId());
  }
  ArrayNode values=nodeFactory.arrayNode();
  values.addAll(capability.getContent().stream().map(this::mapType).collect(Collectors.toList()));
  type.set("values",values);
  parent.set("type",type);
}
