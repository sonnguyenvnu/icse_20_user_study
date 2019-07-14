public static List<FieldInfo> computeGetters(Class<?> clazz,Map<String,String> aliasMap,boolean sorted){
  JSONType jsonType=TypeUtils.getAnnotation(clazz,JSONType.class);
  Map<String,Field> fieldCacheMap=new HashMap<String,Field>();
  ParserConfig.parserAllFieldToCache(clazz,fieldCacheMap);
  return computeGetters(clazz,jsonType,aliasMap,fieldCacheMap,sorted,PropertyNamingStrategy.CamelCase);
}
