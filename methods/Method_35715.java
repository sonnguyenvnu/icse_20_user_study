public static <T>T read(String json,Class<T> clazz){
  try {
    ObjectMapper mapper=getObjectMapper();
    return mapper.readValue(json,clazz);
  }
 catch (  JsonProcessingException processingException) {
    throw JsonException.fromJackson(processingException);
  }
catch (  IOException ioe) {
    return throwUnchecked(ioe,clazz);
  }
}
