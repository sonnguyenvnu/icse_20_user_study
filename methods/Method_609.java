/** 
 * fieldName,field ????fieldName?????????findField???
 * @param clazz
 * @param fieldCacheMap :map&lt;fieldName ,Field&gt;
 */
public static void parserAllFieldToCache(Class<?> clazz,Map<String,Field> fieldCacheMap){
  Field[] fields=clazz.getDeclaredFields();
  for (  Field field : fields) {
    String fieldName=field.getName();
    if (!fieldCacheMap.containsKey(fieldName)) {
      fieldCacheMap.put(fieldName,field);
    }
  }
  if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
    parserAllFieldToCache(clazz.getSuperclass(),fieldCacheMap);
  }
}
