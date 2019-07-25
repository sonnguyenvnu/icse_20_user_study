@Override public JsonNode digest(final JsonNode schema){
  final ObjectNode ret=FACTORY.objectNode();
  final ArrayNode simpleTypes=FACTORY.arrayNode();
  ret.put(keyword,simpleTypes);
  final ArrayNode schemas=FACTORY.arrayNode();
  ret.put("schemas",schemas);
  final JsonNode node=schema.get(keyword);
  final EnumSet<NodeType> set=EnumSet.noneOf(NodeType.class);
  if (node.isTextual())   putType(set,node.textValue());
 else {
    final int size=node.size();
    JsonNode element;
    for (int index=0; index < size; index++) {
      element=node.get(index);
      if (element.isTextual())       putType(set,element.textValue());
 else       schemas.add(index);
    }
  }
  if (EnumSet.complementOf(set).isEmpty())   schemas.removeAll();
  for (  final NodeType type : set)   simpleTypes.add(type.toString());
  return ret;
}
