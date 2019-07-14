public static <T>T castToJavaBean(Object obj,Class<T> clazz){
  return cast(obj,clazz,ParserConfig.getGlobalInstance());
}
