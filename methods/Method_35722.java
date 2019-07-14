public static JsonException fromJackson(JsonProcessingException processingException){
  Throwable rootCause=getRootCause(processingException);
  String message=rootCause.getMessage();
  if (rootCause instanceof PatternSyntaxException) {
    PatternSyntaxException patternSyntaxException=(PatternSyntaxException)rootCause;
    message=patternSyntaxException.getMessage();
  }
 else   if (rootCause instanceof JsonMappingException) {
    message=((JsonMappingException)rootCause).getOriginalMessage();
  }
 else   if (rootCause instanceof InvalidInputException) {
    message=((InvalidInputException)rootCause).getErrors().first().getDetail();
  }
  String pointer=null;
  if (processingException instanceof JsonMappingException) {
    List<String> nodes=transform(((JsonMappingException)processingException).getPath(),TO_NODE_NAMES);
    pointer='/' + Joiner.on('/').join(nodes);
  }
  return new JsonException(Errors.single(10,pointer,"Error parsing JSON",message));
}
