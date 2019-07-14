private static Optional<String> getMappingForFunction(String function){
  return ofNullable(methodMappings.get(function));
}
