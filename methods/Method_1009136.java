@Override public JsonNode digest(final JsonNode schema){
  final ObjectNode ret=FACTORY.objectNode();
  ret.put(keyword,true);
  ret.put("itemsSize",0);
  if (schema.get(keyword).asBoolean(true))   return ret;
  final JsonNode itemsNode=schema.path("items");
  if (!itemsNode.isArray())   return ret;
  ret.put(keyword,false);
  ret.put("itemsSize",itemsNode.size());
  return ret;
}
