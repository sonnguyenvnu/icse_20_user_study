public Class<?> checkAutoType(Class type){
  if (deserializers.get(type) != null) {
    return type;
  }
  return checkAutoType(type.getName(),null,JSON.DEFAULT_PARSER_FEATURE);
}
