static ApiResponse parse(String json) throws Exception {
  ObjectMapper objectMapper=new ObjectMapper();
  objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
  objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
  return objectMapper.readValue(json,ApiResponse.class);
}
