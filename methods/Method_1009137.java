@Override public JsonNode digest(final JsonNode schema){
  final ObjectNode ret=FACTORY.objectNode();
  final ArrayNode properties=FACTORY.arrayNode();
  final ArrayNode patternProperties=FACTORY.arrayNode();
  ret.put(keyword,true);
  ret.put("properties",properties);
  ret.put("patternProperties",patternProperties);
  if (schema.get(keyword).asBoolean(true))   return ret;
  ret.put(keyword,false);
  List<String> list;
  list=Lists.newArrayList(schema.path("properties").fieldNames());
  Collections.sort(list);
  for (  final String s : list)   properties.add(s);
  list=Lists.newArrayList(schema.path("patternProperties").fieldNames());
  Collections.sort(list);
  for (  final String s : list)   patternProperties.add(s);
  return ret;
}
