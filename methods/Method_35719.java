public static String prettyPrint(String json){
  ObjectMapper mapper=getObjectMapper();
  try {
    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readValue(json,JsonNode.class));
  }
 catch (  IOException e) {
    return throwUnchecked(e,String.class);
  }
}
