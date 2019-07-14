private static Class<? extends StringValuePattern> findPatternClass(JsonNode rootNode) throws JsonMappingException {
  for (  Map.Entry<String,JsonNode> node : ImmutableList.copyOf(rootNode.fields())) {
    Class<? extends StringValuePattern> patternClass=PATTERNS.get(node.getKey());
    if (patternClass != null) {
      return patternClass;
    }
  }
  throw new JsonMappingException(rootNode.toString() + " is not a valid match operation");
}
