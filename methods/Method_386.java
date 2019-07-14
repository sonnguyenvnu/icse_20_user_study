public static <T>T toJavaObject(JSON json,Class<T> clazz){
  return TypeUtils.cast(json,clazz,ParserConfig.getGlobalInstance());
}
