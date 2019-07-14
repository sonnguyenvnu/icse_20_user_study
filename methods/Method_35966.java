private BinaryEqualToPattern deserializeBinaryEqualTo(JsonNode rootNode) throws JsonMappingException {
  String operand=rootNode.findValue("binaryEqualTo").textValue();
  return new BinaryEqualToPattern(operand);
}
