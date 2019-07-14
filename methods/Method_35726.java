private static Object getValue(JsonNode node){
  return node.isTextual() ? node.textValue() : node.isNumber() ? node.numberValue() : node.isBoolean() ? node.booleanValue() : node.textValue();
}
