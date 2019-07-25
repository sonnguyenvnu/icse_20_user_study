@Override public JsonNode digest(final JsonNode schema){
  final ObjectNode ret=FACTORY.objectNode();
  final ArrayNode required=FACTORY.arrayNode();
  ret.put("required",required);
  final JsonNode node=schema.get(keyword);
  final List<String> list=Lists.newArrayList(node.fieldNames());
  Collections.sort(list);
  for (  final String field : list)   if (node.get(field).path("required").asBoolean(false))   required.add(field);
  return ret;
}
