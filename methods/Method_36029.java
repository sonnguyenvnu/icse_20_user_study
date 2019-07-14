private static String fromNullableTextNode(JsonNode node){
  return node == null ? null : node.asText();
}
