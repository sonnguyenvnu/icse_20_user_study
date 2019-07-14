private static boolean isJSONTypeIgnore(Class<?> clazz,String propertyName){
  JSONType jsonType=TypeUtils.getAnnotation(clazz,JSONType.class);
  if (jsonType != null) {
    String[] fields=jsonType.includes();
    if (fields.length > 0) {
      for (int i=0; i < fields.length; i++) {
        if (propertyName.equals(fields[i])) {
          return false;
        }
      }
      return true;
    }
 else {
      fields=jsonType.ignores();
      for (int i=0; i < fields.length; i++) {
        if (propertyName.equals(fields[i])) {
          return true;
        }
      }
    }
  }
  if (clazz.getSuperclass() != Object.class && clazz.getSuperclass() != null) {
    if (isJSONTypeIgnore(clazz.getSuperclass(),propertyName)) {
      return true;
    }
  }
  return false;
}
