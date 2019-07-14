@SuppressWarnings("unchecked") public static <T>T stringToBean(String str,Class<T> clazz){
  if (str == null || str.length() <= 0 || clazz == null) {
    return null;
  }
  if (clazz == int.class || clazz == Integer.class) {
    return (T)Integer.valueOf(str);
  }
 else   if (clazz == String.class) {
    return (T)str;
  }
 else   if (clazz == long.class || clazz == Long.class) {
    return (T)Long.valueOf(str);
  }
 else {
    return JSON.toJavaObject(JSON.parseObject(str),clazz);
  }
}
