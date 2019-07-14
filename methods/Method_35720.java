public static <T>T mapToObject(Map<String,Object> map,Class<T> targetClass){
  ObjectMapper mapper=getObjectMapper();
  return mapper.convertValue(map,targetClass);
}
