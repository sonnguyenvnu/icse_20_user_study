/** 
 * ?????map????????
 * @param bean   ??
 * @param prefix ??key???
 * @param map    ????map
 */
public static void copyPropertiesToMap(Object bean,String prefix,Map<String,Object> map){
  Class clazz=bean.getClass();
  Method[] methods=bean.getClass().getMethods();
  for (  Method method : methods) {
    Class returnc=method.getReturnType();
    if (ReflectUtils.isBeanPropertyReadMethod(method)) {
      String propertyName=ReflectUtils.getPropertyNameFromBeanReadMethod(method);
      try {
        if (ReflectUtils.getPropertySetterMethod(clazz,propertyName,returnc) == null) {
          continue;
        }
      }
 catch (      Exception e) {
        continue;
      }
      Object val;
      try {
        val=method.invoke(bean);
      }
 catch (      InvocationTargetException e) {
        throw new SofaRpcRuntimeException("Can't access copy " + propertyName,e.getCause());
      }
catch (      IllegalAccessException e) {
        throw new SofaRpcRuntimeException("Can't access copy " + propertyName,e);
      }
      if (val != null) {
        map.put(prefix + propertyName,val);
      }
    }
  }
  Field[] fields=bean.getClass().getFields();
  for (  Field field : fields) {
    String fieldName=field.getName();
    if (map.containsKey(prefix + fieldName)) {
      continue;
    }
    int m=field.getModifiers();
    if (!Modifier.isStatic(m) && !Modifier.isTransient(m)) {
      Object val=null;
      try {
        if (field.isAccessible()) {
          val=field.get(bean);
        }
 else {
          try {
            field.setAccessible(true);
            val=field.get(bean);
          }
  finally {
            field.setAccessible(false);
          }
        }
      }
 catch (      IllegalAccessException e) {
      }
      if (val != null) {
        map.put(prefix + fieldName,val);
      }
    }
  }
}
