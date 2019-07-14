public static <T>Map<String,Object> objectToMap(T theObject){
  ObjectMapper mapper=getObjectMapper();
  return mapper.convertValue(theObject,new TypeReference<Map<String,Object>>(){
  }
);
}
