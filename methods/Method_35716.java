public static <T>T read(String json,TypeReference<T> typeRef){
  try {
    ObjectMapper mapper=getObjectMapper();
    return mapper.readValue(json,typeRef);
  }
 catch (  JsonProcessingException processingException) {
    throw JsonException.fromJackson(processingException);
  }
catch (  IOException ioe) {
    return throwUnchecked(ioe,(Class<T>)typeRef.getType());
  }
}
