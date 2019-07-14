public static Object deepSearchInMap(Map<String,Object> fieldsMap,String field){
  if (field.contains(".")) {
    String[] split=field.split("\\.");
    return searchPathInMap(fieldsMap,split);
  }
  return fieldsMap.get(field);
}
