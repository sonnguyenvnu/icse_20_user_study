private static ObjectMapper initWithIndentation(){
  return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).enable(SerializationFeature.INDENT_OUTPUT);
}
