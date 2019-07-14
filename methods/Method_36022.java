public StringValuePattern buildStringValuePattern(JsonNode rootNode) throws JsonMappingException {
  Class<? extends StringValuePattern> patternClass=findPatternClass(rootNode);
  if (patternClass.equals(EqualToJsonPattern.class)) {
    return deserializeEqualToJson(rootNode);
  }
 else   if (patternClass.equals(EqualToXmlPattern.class)) {
    return deserializeEqualToXml(rootNode);
  }
 else   if (patternClass.equals(MatchesJsonPathPattern.class)) {
    return deserialiseMatchesJsonPathPattern(rootNode);
  }
 else   if (patternClass.equals(MatchesXPathPattern.class)) {
    return deserialiseMatchesXPathPattern(rootNode);
  }
 else   if (patternClass.equals(EqualToPattern.class)) {
    return deserializeEqualTo(rootNode);
  }
  Constructor<? extends StringValuePattern> constructor=findConstructor(patternClass);
  Map.Entry<String,JsonNode> entry=find(rootNode.fields(),new Predicate<Map.Entry<String,JsonNode>>(){
    @Override public boolean apply(    Map.Entry<String,JsonNode> input){
      return PATTERNS.keySet().contains(input.getKey());
    }
  }
);
  String operand=entry.getValue().textValue();
  try {
    return constructor.newInstance(operand);
  }
 catch (  Exception e) {
    return throwUnchecked(e,StringValuePattern.class);
  }
}
