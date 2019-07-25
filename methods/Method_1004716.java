protected void text(ObjectNode parent,TextCapability capability){
  ObjectNode text=nodeFactory.objectNode();
  text.put("type",capability.getType().getName());
  String defaultValue=capability.getContent();
  if (StringUtils.hasText(defaultValue)) {
    text.put("default",defaultValue);
  }
  parent.set(capability.getId(),text);
}
