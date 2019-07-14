private MatchesJsonPathPattern deserialiseMatchesJsonPathPattern(JsonNode rootNode) throws JsonMappingException {
  if (!rootNode.has("matchesJsonPath")) {
    throw new JsonMappingException(rootNode.toString() + " is not a valid match operation");
  }
  JsonNode outerPatternNode=rootNode.findValue("matchesJsonPath");
  if (outerPatternNode.isTextual()) {
    return new MatchesJsonPathPattern(outerPatternNode.textValue());
  }
  if (!outerPatternNode.has("expression")) {
    throw new JsonMappingException("expression is required in the advanced matchesJsonPath form");
  }
  String expression=outerPatternNode.findValue("expression").textValue();
  StringValuePattern valuePattern=buildStringValuePattern(outerPatternNode);
  return new MatchesJsonPathPattern(expression,valuePattern);
}
