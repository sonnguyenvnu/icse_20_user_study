private static ObjectMapper initWithoutIndentation(){
  return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
}
