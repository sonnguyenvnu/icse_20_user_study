@Override public JsonNode digest(final JsonNode schema){
  final ObjectNode ret=FACTORY.objectNode();
  final ArrayNode allowedTypes=FACTORY.arrayNode();
  ret.put(keyword,allowedTypes);
  final JsonNode node=schema.get(keyword);
  final EnumSet<NodeType> typeSet=EnumSet.noneOf(NodeType.class);
  if (node.isTextual())   typeSet.add(NodeType.fromName(node.textValue()));
 else   for (  final JsonNode element : node)   typeSet.add(NodeType.fromName(element.textValue()));
  if (typeSet.contains(NodeType.NUMBER))   typeSet.add(NodeType.INTEGER);
  for (  final NodeType type : typeSet)   allowedTypes.add(type.toString());
  return ret;
}
