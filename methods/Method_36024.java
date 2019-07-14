private EqualToXmlPattern deserializeEqualToXml(JsonNode rootNode) throws JsonMappingException {
  if (!rootNode.has("equalToXml")) {
    throw new JsonMappingException(rootNode.toString() + " is not a valid match operation");
  }
  JsonNode operand=rootNode.findValue("equalToXml");
  Boolean enablePlaceholders=fromNullable(rootNode.findValue("enablePlaceholders"));
  String placeholderOpeningDelimiterRegex=fromNullableTextNode(rootNode.findValue("placeholderOpeningDelimiterRegex"));
  String placeholderClosingDelimiterRegex=fromNullableTextNode(rootNode.findValue("placeholderClosingDelimiterRegex"));
  return new EqualToXmlPattern(operand.textValue(),enablePlaceholders,placeholderOpeningDelimiterRegex,placeholderClosingDelimiterRegex);
}
