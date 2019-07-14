private static Boolean fromNullable(JsonNode node){
  return node == null ? null : node.asBoolean();
}
