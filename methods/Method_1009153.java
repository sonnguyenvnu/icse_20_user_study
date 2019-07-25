@Override public JsonNode digest(final JsonNode schema){
  final ObjectNode ret=FACTORY.objectNode();
  ret.put("itemsSize",0);
  ret.put("itemsIsArray",false);
  final JsonNode itemsNode=schema.path("items");
  final JsonNode additionalNode=schema.path("additionalItems");
  final boolean hasItems=!itemsNode.isMissingNode();
  final boolean hasAdditional=additionalNode.isObject();
  ret.put("hasItems",hasItems);
  ret.put("hasAdditional",hasAdditional);
  if (itemsNode.isArray()) {
    ret.put("itemsIsArray",true);
    ret.put("itemsSize",itemsNode.size());
  }
  return ret;
}
