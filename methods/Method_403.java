private static void paths(Map<Object,String> values,Map<String,Object> paths,String parent,Object javaObject,SerializeConfig config){
  if (javaObject == null) {
    return;
  }
  String p=values.put(javaObject,parent);
  if (p != null) {
    boolean basicType=javaObject instanceof String || javaObject instanceof Number || javaObject instanceof Date || javaObject instanceof UUID;
    if (!basicType) {
      return;
    }
  }
  paths.put(parent,javaObject);
  if (javaObject instanceof Map) {
    Map map=(Map)javaObject;
    for (    Object entryObj : map.entrySet()) {
      Map.Entry entry=(Map.Entry)entryObj;
      Object key=entry.getKey();
      if (key instanceof String) {
        String path=parent.equals("/") ? "/" + key : parent + "/" + key;
        paths(values,paths,path,entry.getValue(),config);
      }
    }
    return;
  }
  if (javaObject instanceof Collection) {
    Collection collection=(Collection)javaObject;
    int i=0;
    for (    Object item : collection) {
      String path=parent.equals("/") ? "/" + i : parent + "/" + i;
      paths(values,paths,path,item,config);
      ++i;
    }
    return;
  }
  Class<?> clazz=javaObject.getClass();
  if (clazz.isArray()) {
    int len=Array.getLength(javaObject);
    for (int i=0; i < len; ++i) {
      Object item=Array.get(javaObject,i);
      String path=parent.equals("/") ? "/" + i : parent + "/" + i;
      paths(values,paths,path,item,config);
    }
    return;
  }
  if (ParserConfig.isPrimitive2(clazz) || clazz.isEnum()) {
    return;
  }
  ObjectSerializer serializer=config.getObjectWriter(clazz);
  if (serializer instanceof JavaBeanSerializer) {
    JavaBeanSerializer javaBeanSerializer=(JavaBeanSerializer)serializer;
    try {
      Map<String,Object> fieldValues=javaBeanSerializer.getFieldValuesMap(javaObject);
      for (      Map.Entry<String,Object> entry : fieldValues.entrySet()) {
        String key=entry.getKey();
        if (key instanceof String) {
          String path=parent.equals("/") ? "/" + key : parent + "/" + key;
          paths(values,paths,path,entry.getValue(),config);
        }
      }
    }
 catch (    Exception e) {
      throw new JSONException("toJSON error",e);
    }
    return;
  }
  return;
}
