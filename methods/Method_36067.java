public static long parseJsonNode(JsonNode node){
  if (node == null || node.isNull()) {
    return Long.MAX_VALUE;
  }
 else   if (node.isNumber()) {
    return node.asLong();
  }
 else {
    return parseFilesize(node.textValue());
  }
}
