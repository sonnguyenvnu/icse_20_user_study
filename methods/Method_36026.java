private MatchesXPathPattern deserialiseMatchesXPathPattern(JsonNode rootNode) throws JsonMappingException {
  if (!rootNode.has("matchesXPath")) {
    throw new JsonMappingException(rootNode.toString() + " is not a valid match operation");
  }
  JsonNode namespacesNode=rootNode.findValue("xPathNamespaces");
  Map<String,String> namespaces=namespacesNode != null ? toNamespaceMap(namespacesNode) : Collections.<String,String>emptyMap();
  JsonNode outerPatternNode=rootNode.findValue("matchesXPath");
  if (outerPatternNode.isTextual()) {
    return new MatchesXPathPattern(outerPatternNode.textValue(),namespaces);
  }
  if (!outerPatternNode.has("expression")) {
    throw new JsonMappingException("expression is required in the advanced matchesXPath form");
  }
  String expression=outerPatternNode.findValue("expression").textValue();
  StringValuePattern valuePattern=buildStringValuePattern(outerPatternNode);
  return new MatchesXPathPattern(expression,namespaces,valuePattern);
}
