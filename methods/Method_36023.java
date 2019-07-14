private EqualToPattern deserializeEqualTo(JsonNode rootNode) throws JsonMappingException {
  if (!rootNode.has("equalTo")) {
    throw new JsonMappingException(rootNode.toString() + " is not a valid match operation");
  }
  String operand=rootNode.findValue("equalTo").textValue();
  Boolean ignoreCase=fromNullable(rootNode.findValue("caseInsensitive"));
  return new EqualToPattern(operand,ignoreCase);
}
